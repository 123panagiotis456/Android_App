package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.supermarketmanager.data.repository.WishlistRepository

/**
 * Factory for creating WishlistViewModel instances with the required repository dependency.
 */
class WishlistViewModelFactory(
    private val repository: WishlistRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Handles ViewModel instantiation with exception safety
        return try {
            if (modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                WishlistViewModel(repository) as T
            } else {
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } catch (e: Exception) {
            throw RuntimeException("Error creating WishlistViewModel", e)
        }
    }
}
