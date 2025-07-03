package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.data.repository.WishlistRepository
import kotlinx.coroutines.launch

class WishlistViewModel(
    private val repository: WishlistRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<WishlistItemEntity>>()
    val items: LiveData<List<WishlistItemEntity>> get() = _items

    fun loadItems() {
        viewModelScope.launch {
            _items.value = repository.getAllFavorites()
        }
    }

    fun removeItem(itemId: Int) {
        viewModelScope.launch {
            repository.removeFromWishlistById(itemId)
            loadItems() // refresh
        }
    }

    fun toggleFavorite(productId: Int, onToggle: (Boolean) -> Unit = {}) {
        viewModelScope.launch {
            val isFav = repository.isFavorite(productId)
            if (isFav) {
                repository.removeFromWishlist(productId)
            } else {
                repository.addToWishlist(productId)
            }
            onToggle(!isFav)
        }
    }

    suspend fun isFavorite(productId: Int): Boolean {
        return repository.isFavorite(productId)
    }
}

