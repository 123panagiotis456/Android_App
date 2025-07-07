package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import kotlinx.coroutines.launch
import android.util.Log

class PurchaseHistoryViewModel : ViewModel() {

    private val _purchaseHistory = MutableLiveData<List<PurchaseHistoryEntity>>()
    val purchaseHistory: LiveData<List<PurchaseHistoryEntity>> = _purchaseHistory

    fun loadPurchaseHistory() {
        viewModelScope.launch {
            try {
                val purchases = MyApplication.database.purchaseHistoryDao().getAll()
                _purchaseHistory.postValue(purchases)
            } catch (e: Exception) {
                Log.e("PurchaseHistoryViewModel", "Error loading purchase history", e)
                _purchaseHistory.postValue(emptyList())
            }
        }
    }
}
