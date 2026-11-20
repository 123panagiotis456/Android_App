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

    suspend fun toggleFavorite(productId: Int): Boolean {
        val favorite = repository.isFavorite(productId)
        if (favorite) {
            repository.removeFromWishlist(productId)
        } else {
            repository.addToWishlist(productId)
        }
        return !favorite
    }

    suspend fun isFavorite(productId: Int): Boolean = repository.isFavorite(productId)
}
