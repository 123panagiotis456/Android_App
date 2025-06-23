package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.MyApplication
import kotlinx.coroutines.launch

class PurchaseHistoryViewModel : ViewModel() {
    private val _history = MutableLiveData<List<PurchaseHistoryEntity>>()
    val history: LiveData<List<PurchaseHistoryEntity>> = _history

    fun loadHistory() = viewModelScope.launch {
        _history.postValue(MyApplication.database.purchaseHistoryDao().getAll())
    }
}
