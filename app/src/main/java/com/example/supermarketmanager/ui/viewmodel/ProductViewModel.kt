package com.example.supermarketmanager.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.data.repository.CartRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProductViewModel(private val savedState: SavedStateHandle) : ViewModel() {

    private val database = MyApplication.database
    private val dao = database.productDao()
    val cartItems = CartRepository.observeItems(database).asLiveData()

    private val _products = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>> = _products
    private val _cartErrors = MutableSharedFlow<CartError>()
    val cartErrors = _cartErrors.asSharedFlow()

    enum class CartError { STOCK, UPDATE }

    val byPrice: Boolean get() = savedState["by_price"] ?: false
    val availableOnly: Boolean get() = savedState["available_only"] ?: false
    val search: String get() = savedState["search"] ?: ""
    private var categoryId: Int? = null
    private var catalogJob: Job? = null

    fun getById(productId: Int) = dao.observeById(productId).asLiveData()

    fun loadCatalog(category: Int) {
        categoryId = category
        refreshCatalog()
    }

    fun applyFilters(byPrice: Boolean, availableOnly: Boolean) {
        savedState["by_price"] = byPrice
        savedState["available_only"] = availableOnly
        refreshCatalog()
    }

    fun searchProducts(query: String) {
        val trimmed = query.trim()
        if (search == trimmed) return
        savedState["search"] = trimmed
        refreshCatalog()
    }

    private fun refreshCatalog() {
        val category = categoryId ?: return
        catalogJob?.cancel()
        val sortByPrice = byPrice
        val onlyAvailable = availableOnly
        val query = search
        catalogJob = viewModelScope.launch {
            try {
                dao.observeCatalog(category, onlyAvailable).collect { products ->
                    val matches = products.filter { it.name.contains(query, ignoreCase = true) }
                    _products.value = if (sortByPrice) matches.sortedBy { it.pricePerUnit } else matches
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading catalog", e)
                _products.value = emptyList()
            }
        }
    }

    fun addOneToCart(productId: Int) = adjustCartQuantity(productId, 1)

    fun removeOneFromCart(productId: Int) = adjustCartQuantity(productId, -1)

    suspend fun setCartQuantity(productId: Int, quantity: Int) =
        CartRepository.setQuantity(database, productId, quantity)

    private fun adjustCartQuantity(productId: Int, delta: Int) {
        viewModelScope.launch {
            try {
                if (!CartRepository.adjustQuantity(database, productId, delta)) {
                    _cartErrors.emit(CartError.STOCK)
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error updating cart quantity", e)
                _cartErrors.emit(CartError.UPDATE)
            }
        }
    }
}
