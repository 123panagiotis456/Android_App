package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import com.example.supermarketmanager.MyApplication
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {
    private val _items = MutableLiveData<List<ShoppingListItemEntity>>()
    val items: LiveData<List<ShoppingListItemEntity>> = _items

    fun loadItems() = viewModelScope.launch {
        _items.postValue(MyApplication.database.shoppingListDao().getAll())
    }

    fun addItem(item: ShoppingListItemEntity) = viewModelScope.launch {
        MyApplication.database.shoppingListDao().addItem(item)
        loadItems()
    }

    fun removeItem(itemId: Int) = viewModelScope.launch {
        MyApplication.database.shoppingListDao().removeItem(itemId)
        loadItems()
    }
}
