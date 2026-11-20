package com.example.supermarketmanager.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch

class PurchaseHistoryViewModel : ViewModel() {

    data class History(
        val purchases: List<PurchaseHistoryEntity>,
        val productNames: Map<Int, String>
    )

    private val _history = MutableLiveData<Result<History>>()
    val history: LiveData<Result<History>> = _history

    fun loadPurchaseHistory() {
        viewModelScope.launch {
            try {
                val database = MyApplication.database
                val purchases = database.purchaseHistoryDao().getAll()
                val productIds = purchases.flatMap { it.productIds }.distinct()
                val productNames = database.productDao().getByIdList(productIds)
                    .associate { it.id to it.name }
                _history.value = Result.success(History(purchases, productNames))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("PurchaseHistoryViewModel", "Error loading purchase history", e)
                _history.value = Result.failure(e)
            }
        }
    }
}
