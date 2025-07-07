package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.data.models.ShoppingCartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity

class ShoppingListViewModel : ViewModel() {

    private val _cartItems = MutableLiveData<List<ShoppingCartItem>>()
    val cartItems: LiveData<List<ShoppingCartItem>> = _cartItems

    fun loadCartItems() {
        MyApplication.database.shoppingListDao().getItemsWithProductDetails()
            .onEach { items ->
                _cartItems.postValue(items)
            }
            .launchIn(viewModelScope)
    }

    fun makePurchase() = viewModelScope.launch(Dispatchers.IO) {
        val shoppingListDao = MyApplication.database.shoppingListDao()
        val productDao = MyApplication.database.productDao()
        val purchaseHistoryDao = MyApplication.database.purchaseHistoryDao()

        val cartItems = shoppingListDao.getAll()
        if (cartItems.isEmpty()) return@launch

        val productIds = cartItems.map { it.productId }
        val products = productDao.getByIdList(productIds)
        val productMap = products.associateBy { it.id }

        val purchasedProductIds = mutableListOf<Int>()
        val purchasedPrices = mutableListOf<Double>()
        val purchasedQuantities = mutableListOf<Int>()
        var totalCost = 0.0

        for (item in cartItems) {
            val product = productMap[item.productId] ?: continue

            // --- ΜΕΙΩΣΗ ΔΙΑΘΕΣΙΜΟΤΗΤΑΣ ---
            if (item.quantity <= product.availability) {
                productDao.decreaseAvailability(product.id, item.quantity)
            }
            // --------------------------------

            purchasedProductIds.add(product.id)
            purchasedPrices.add(product.pricePerUnit)
            purchasedQuantities.add(item.quantity)
            totalCost += product.pricePerUnit * item.quantity
        }

        val purchase = PurchaseHistoryEntity(
            timestamp = System.currentTimeMillis(),
            totalCost = totalCost,
            productIds = purchasedProductIds,
            prices = purchasedPrices,
            quantities = purchasedQuantities
        )

        purchaseHistoryDao.insert(purchase)
        shoppingListDao.clear()

        loadCartItems()
    }

    /** Διαγράφει όλα τα προϊόντα από το καλάθι */
    fun clearCart() = viewModelScope.launch(Dispatchers.IO) {
        val shoppingListDao = MyApplication.database.shoppingListDao()
        shoppingListDao.clear()
        loadCartItems()
    }

    /** Drop all items from a past purchase back into the cart */
    fun readdPurchaseToCart(purchase: PurchaseHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        val dao = MyApplication.database.shoppingListDao()
        purchase.productIds.forEachIndexed { index, productId ->
            val qty = purchase.quantities[index]
            val current = dao.getItemByProductId(productId)
            if (current != null) {
                dao.updateQuantity(productId, current.quantity + qty)
            } else {
                dao.insert(ShoppingListItemEntity(productId = productId, quantity = qty))
            }
        }
        loadCartItems()
    }
}
