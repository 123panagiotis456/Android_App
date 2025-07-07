package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import com.example.supermarketmanager.data.models.ShoppingCartItem
import kotlinx.coroutines.launch
import android.util.Log

class ProductViewModel : ViewModel() {

    // DAO references
    private val dao = MyApplication.database.productDao()
    private val shoppingListDao = MyApplication.database.shoppingListDao()

    // Real-time καρότσι
    val cartItems: LiveData<List<ShoppingCartItem>> =
        shoppingListDao.getItemsWithProductDetails().asLiveData()

    // Λίστα προϊόντων για οθόνη λίστας
    private val _products = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>> = _products

    // Ένα προϊόν για οθόνη λεπτομερειών
    fun getById(productId: Int): LiveData<ProductEntity?> = liveData {
        val product = dao.getById(productId)
        emit(product)
    }

    fun filterProducts(categoryId: Int?, maxPrice: Double?, availableOnly: Boolean?) {
        viewModelScope.launch {
            val result = if (availableOnly != null) {
                dao.getFilteredWithAvailability(categoryId, maxPrice, availableOnly)
            } else {
                dao.getFilteredWithoutAvailability(categoryId, maxPrice)
            }
            Log.d("DBG", "query returned ${result.size} προϊόντα")
            _products.postValue(result)
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            val result = dao.search(query)
            _products.postValue(result)
        }
    }

    fun addOneToCart(productId: Int) {
        viewModelScope.launch {
            val currentItems = shoppingListDao.getAll()
            val existing = currentItems.find { it.productId == productId }

            if (existing != null) {
                val updated = existing.copy(quantity = existing.quantity + 1)
                shoppingListDao.update(updated)
            } else {
                val newItem = ShoppingListItemEntity(productId = productId, quantity = 1)
                shoppingListDao.insert(newItem)
            }
        }
    }
    fun setCartQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            val currentItems = shoppingListDao.getAll()
            val existing = currentItems.find { it.productId == productId }

            if (quantity > 0) {
                if (existing != null) {
                    shoppingListDao.update(existing.copy(quantity = quantity))
                } else {
                    shoppingListDao.insert(
                        ShoppingListItemEntity(productId = productId, quantity = quantity)
                    )
                }
            } else {
                if (existing != null) {
                    shoppingListDao.delete(existing)
                }
            }
        }
    }

    fun removeOneFromCart(productId: Int) {
        viewModelScope.launch {
            val currentItems = shoppingListDao.getAll()
            val existing = currentItems.find { it.productId == productId }

            if (existing != null) {
                if (existing.quantity > 1) {
                    val updated = existing.copy(quantity = existing.quantity - 1)
                    shoppingListDao.update(updated)
                } else {
                    shoppingListDao.delete(existing)
                }
            }
        }
    }
    fun updateCartItemQuantity(productId: Int, newQuantity: Int) {
        viewModelScope.launch {
            val cartItem = shoppingListDao.getItemByProductId(productId)
            if (cartItem != null) {
                shoppingListDao.updateQuantity(productId, newQuantity)
            }
        }
    }

    // Ταξινόμηση: Προκαθορισμένη (default)
    fun sortProductsDefault() {
        _products.value?.let { list ->
            _products.postValue(list.sortedBy { it.id })
        }
    }

    // Ταξινόμηση: Τιμή πώλησης (αύξουσα)
    fun sortProductsByPrice() {
        _products.value?.let { list ->
            _products.postValue(list.sortedBy { it.pricePerUnit })
        }
    }

    // Ταξινόμηση: Ποσοστό έκπτωσης (όσα έχουν offer πάνω πάνω)
    fun sortProductsByDiscount() {
        _products.value?.let { list ->
            _products.postValue(
                list.sortedByDescending { !it.offer.isNullOrBlank() }
            )
        }
    }

    // Ταξινόμηση: Τιμή μονάδας (βάσει pricePerUnit, αφού δεν έχεις extra πεδίο)
    fun sortProductsByUnitPrice() {
        _products.value?.let { list ->
            _products.postValue(list.sortedBy { it.pricePerUnit })
        }
    }
}
