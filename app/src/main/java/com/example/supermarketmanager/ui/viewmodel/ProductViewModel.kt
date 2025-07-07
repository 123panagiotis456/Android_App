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
        try {
            val product = dao.getById(productId)
            emit(product)
        } catch (e: Exception) {
            Log.e("ProductViewModel", "Error loading product by ID", e)
            emit(null)
        }
    }

    fun filterProducts(categoryId: Int?, maxPrice: Double?, availableOnly: Boolean?) {
        viewModelScope.launch {
            try {
                val result = if (availableOnly != null) {
                    dao.getFilteredWithAvailability(categoryId, maxPrice, availableOnly)
                } else {
                    dao.getFilteredWithoutAvailability(categoryId, maxPrice)
                }
                Log.d("DBG", "query returned ${result.size} προϊόντα")
                _products.postValue(result)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error filtering products", e)
                _products.postValue(emptyList())
            }
        }
    }

    fun searchProducts(query: String) {
        viewModelScope.launch {
            try {
                val result = dao.search(query)
                _products.postValue(result)
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error searching products", e)
                _products.postValue(emptyList())
            }
        }
    }

    fun addOneToCart(productId: Int) {
        viewModelScope.launch {
            try {
                val currentItems = shoppingListDao.getAll()
                val existing = currentItems.find { it.productId == productId }

                if (existing != null) {
                    val updated = existing.copy(quantity = existing.quantity + 1)
                    shoppingListDao.update(updated)
                } else {
                    val newItem = ShoppingListItemEntity(productId = productId, quantity = 1)
                    shoppingListDao.insert(newItem)
                }
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error adding one to cart", e)
            }
        }
    }

    fun setCartQuantity(productId: Int, quantity: Int) {
        viewModelScope.launch {
            try {
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
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error setting cart quantity", e)
            }
        }
    }

    fun removeOneFromCart(productId: Int) {
        viewModelScope.launch {
            try {
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
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error removing one from cart", e)
            }
        }
    }

    fun updateCartItemQuantity(productId: Int, newQuantity: Int) {
        viewModelScope.launch {
            try {
                val cartItem = shoppingListDao.getItemByProductId(productId)
                if (cartItem != null) {
                    shoppingListDao.updateQuantity(productId, newQuantity)
                }
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error updating cart item quantity", e)
            }
        }
    }

    fun sortProductsDefault() {
        try {
            _products.value?.let { list ->
                _products.postValue(list.sortedBy { it.id })
            }
        } catch (e: Exception) {
            Log.e("ProductViewModel", "Error sorting products (default)", e)
        }
    }

    fun sortProductsByPrice() {
        try {
            _products.value?.let { list ->
                _products.postValue(list.sortedBy { it.pricePerUnit })
            }
        } catch (e: Exception) {
            Log.e("ProductViewModel", "Error sorting products by price", e)
        }
    }

    fun sortProductsByDiscount() {
        try {
            _products.value?.let { list ->
                _products.postValue(
                    list.sortedByDescending { !it.offer.isNullOrBlank() }
                )
            }
        } catch (e: Exception) {
            Log.e("ProductViewModel", "Error sorting products by discount", e)
        }
    }

    fun sortProductsByUnitPrice() {
        try {
            _products.value?.let { list ->
                _products.postValue(list.sortedBy { it.pricePerUnit })
            }
        } catch (e: Exception) {
            Log.e("ProductViewModel", "Error sorting products by unit price", e)
        }
    }
}
