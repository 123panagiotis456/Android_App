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

    data class OrderLine(
        val productId: Int,
        val name: String?,
        val quantity: Int,
        val unitPrice: Double,
        val inStock: Boolean
    )

    data class Order(val purchase: PurchaseHistoryEntity, val lines: List<OrderLine>)

    private val _history = MutableLiveData<Result<List<PurchaseHistoryEntity>>>()
    val history: LiveData<Result<List<PurchaseHistoryEntity>>> = _history

    fun loadPurchaseHistory() {
        viewModelScope.launch {
            try {
                val purchases = MyApplication.database.purchaseHistoryDao().getAll()
                _history.value = Result.success(purchases)
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.e("PurchaseHistoryViewModel", "Error loading purchase history", e)
                _history.value = Result.failure(e)
            }
        }
    }

    suspend fun loadOrder(id: Int): Order? {
        val database = MyApplication.database
        val purchase = database.purchaseHistoryDao().getById(id) ?: return null
        val ids = purchase.productIds
        if (ids.isEmpty() || ids.size != purchase.quantities.size || ids.size != purchase.prices.size) {
            return Order(purchase, emptyList())
        }
        val products = database.productDao().getByIdList(ids.distinct()).associateBy { it.id }
        return Order(purchase, ids.mapIndexed { index, productId ->
            val product = products[productId]
            OrderLine(
                productId, product?.name, purchase.quantities[index], purchase.prices[index],
                product != null && product.availability > 0
            )
        })
    }
}
