package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.models.WishlistProductItem
import com.example.supermarketmanager.data.repository.WishlistRepository
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val repository: WishlistRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<WishlistProductItem>>()
    val items: LiveData<List<WishlistProductItem>> get() = _items

    fun loadItems() {
        viewModelScope.launch {
            _items.value = repository.getWishlistWithProducts()
        }
    }

    fun removeItem(wishlistId: Int) {
        viewModelScope.launch {
            repository.removeFromWishlistById(wishlistId)
            loadItems()
        }
    }

    // Προαιρετικά: toggle και έλεγχος favorite (χρησιμοποιείται αν έχεις toggle καρδούλα σε όλα τα προϊόντα)
    fun toggleFavorite(productId: Int, onToggle: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            val isFav = repository.isFavorite(productId)
            if (isFav) {
                repository.removeFromWishlist(productId)
            } else {
                repository.addToWishlist(productId)
            }
            loadItems() // Update immediately!
            onToggle(!isFav)
        }
    }

    suspend fun isFavorite(productId: Int): Boolean {
        return repository.isFavorite(productId)
    }
}
