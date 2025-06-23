package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.MyApplication
import kotlinx.coroutines.launch

class WishlistViewModel : ViewModel() {
    private val _items = MutableLiveData<List<WishlistItemEntity>>()
    val items: LiveData<List<WishlistItemEntity>> = _items

    fun loadItems() = viewModelScope.launch {
        _items.postValue(MyApplication.database.wishlistDao().getAll())
    }

    fun addItem(item: WishlistItemEntity) = viewModelScope.launch {
        MyApplication.database.wishlistDao().addItem(item)
        loadItems()
    }

    fun removeItem(itemId: Int) = viewModelScope.launch {
        MyApplication.database.wishlistDao().removeItem(itemId)
        loadItems()
    }
}
