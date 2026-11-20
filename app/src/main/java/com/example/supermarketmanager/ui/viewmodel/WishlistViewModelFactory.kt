package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.repository.WishlistRepository

class WishlistViewModelFactory(
    private val repository: WishlistRepository = WishlistRepository(MyApplication.database.wishlistDao())
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(WishlistViewModel::class.java)) {
            "Unknown ViewModel class: ${modelClass.name}"
        }
        @Suppress("UNCHECKED_CAST")
        return WishlistViewModel(repository) as T
    }
}
