package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.models.WishlistProductItem
import com.example.supermarketmanager.data.repository.WishlistRepository
import kotlinx.coroutines.launch
import android.util.Log

class WishlistViewModel(
    private val repository: WishlistRepository
) : ViewModel() {

    // Holds the wishlist items with product details for UI observation
    private val _items = MutableLiveData<List<WishlistProductItem>>()
    val items: LiveData<List<WishlistProductItem>> get() = _items

    /**
     * Loads all wishlist items with product details.
     * Handles and logs any exception that may occur.
     */
    fun loadItems() {
        viewModelScope.launch {
            try {
                _items.value = repository.getWishlistWithProducts()
            } catch (e: Exception) {
                Log.e("WishlistViewModel", "Error loading wishlist items", e)
                _items.value = emptyList()
            }
        }
    }

    /**
     * Removes an item from the wishlist by its wishlist id.
     * Updates the LiveData after the operation.
     */
    fun removeItem(wishlistId: Int) {
        viewModelScope.launch {
            try {
                repository.removeFromWishlistById(wishlistId)
            } catch (e: Exception) {
                Log.e("WishlistViewModel", "Error removing wishlist item", e)
            }
            loadItems()
        }
    }

    /**
     * Toggles favorite state for a given product.
     * Calls onToggle callback with the new state.
     */
    fun toggleFavorite(productId: Int, onToggle: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            try {
                val isFav = repository.isFavorite(productId)
                if (isFav) {
                    repository.removeFromWishlist(productId)
                } else {
                    repository.addToWishlist(productId)
                }
                loadItems() // Always refresh UI
                onToggle(!isFav)
            } catch (e: Exception) {
                Log.e("WishlistViewModel", "Error toggling favorite", e)
                onToggle(false)
            }
        }
    }

    /**
     * Checks if a product is favorite. Handles any exception and returns false in error case.
     */
    suspend fun isFavorite(productId: Int): Boolean {
        return try {
            repository.isFavorite(productId)
        } catch (e: Exception) {
            Log.e("WishlistViewModel", "Error checking if product is favorite", e)
            false
        }
    }
}
