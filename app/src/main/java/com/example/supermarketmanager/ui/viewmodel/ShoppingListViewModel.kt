package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.supermarketmanager.MyApplication
import com.example.supermarketmanager.data.repository.CartRepository

class ShoppingListViewModel : ViewModel() {

    private val database = MyApplication.database

    val cartItems = CartRepository.observeItems(database).asLiveData()

    suspend fun makePurchase() = CartRepository.makePurchase(database)

    suspend fun clearCart() = CartRepository.clear(database)

    suspend fun addOneIfAvailable(productId: Int) =
        CartRepository.addOneIfAvailable(database, productId)
}
