package com.example.supermarketmanager.data.repository

import android.content.Context
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity

object CartRepository {
    suspend fun addProductToCart(context: Context, productId: Int) {
        val dao = AppDatabase.getInstance(context).shoppingListDao()
        val currentItems = dao.getAll()
        val existingItem = currentItems.find { it.productId == productId }

        if (existingItem != null) {
            val updated = existingItem.copy(quantity = existingItem.quantity + 1)
            dao.update(updated)
        } else {
            dao.insert(ShoppingListItemEntity(productId = productId, quantity = 1))
        }
    }
}