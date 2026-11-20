package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.asLiveData
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.data.repository.CartRepository
import com.example.supermarketmanager.data.models.ShoppingCartItem
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import android.util.Log

class ProductViewModel : ViewModel() {

    // DAO references
    private val dao = MyApplication.database.productDao()

    // Real-time καρότσι
    val cartItems: LiveData<List<ShoppingCartItem>> =
        CartRepository.observeItems(MyApplication.database).asLiveData()

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

    fun addOneToCart(productId: Int) = adjustCartQuantity(productId, 1)

    fun removeOneFromCart(productId: Int) = adjustCartQuantity(productId, -1)

    fun decreaseCartQuantity(productId: Int) = adjustCartQuantity(productId, -1, minimum = 1)

    suspend fun setCartQuantity(productId: Int, quantity: Int) =
        CartRepository.setQuantity(MyApplication.database, productId, quantity)

    private fun adjustCartQuantity(productId: Int, delta: Int, minimum: Int = 0) {
        viewModelScope.launch {
            try {
                CartRepository.adjustQuantity(MyApplication.database, productId, delta, minimum)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error updating cart quantity", e)
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
