package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import com.example.supermarketmanager.MyApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingListViewModel : ViewModel() {
    private val _items = MutableLiveData<List<ShoppingListItemEntity>>()
    val items: LiveData<List<ShoppingListItemEntity>> = _items

    fun loadItems() = viewModelScope.launch(Dispatchers.IO) {
        val data = MyApplication.database.shoppingListDao().getAll()
        _items.postValue(data)
    }

    fun addItem(item: ShoppingListItemEntity) = viewModelScope.launch(Dispatchers.IO) {
        MyApplication.database.shoppingListDao().insert(item)
        loadItems()
    }

    fun removeItem(item: ShoppingListItemEntity) = viewModelScope.launch(Dispatchers.IO) {
        MyApplication.database.shoppingListDao().delete(item)
        loadItems()
    }

}
