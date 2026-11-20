package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.asLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.data.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import android.util.Log

class ShoppingListViewModel : ViewModel() {

    val cartItems = MyApplication.database.shoppingListDao()
        .getItemsWithProductDetails()
        .asLiveData()

    suspend fun makePurchase() = CartRepository.makePurchase(MyApplication.database)

    suspend fun clearCart() = MyApplication.database.shoppingListDao().clear()

    /** Drop all items from a past purchase back into the cart */
    fun readdPurchaseToCart(purchase: PurchaseHistoryEntity) = viewModelScope.launch(Dispatchers.IO) {
        try {
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
        } catch (e: Exception) {
            Log.e("ShoppingListViewModel", "Error readding purchase to cart", e)
        }
    }
}
