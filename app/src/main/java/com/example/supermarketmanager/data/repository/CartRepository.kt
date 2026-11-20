package com.example.supermarketmanager.data.repository

import android.content.Context
import androidx.room.withTransaction
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
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

    class InsufficientStock(val productName: String, val available: Int) : Exception()

    suspend fun makePurchase(database: AppDatabase): Boolean = database.withTransaction {
        val cartDao = database.shoppingListDao()
        val quantities = cartDao.getAll()
            .groupingBy { it.productId }
            .fold(0L) { total, item ->
                check(item.quantity > 0)
                total + item.quantity
            }
        if (quantities.isEmpty()) return@withTransaction false

        val productDao = database.productDao()
        val products = productDao.getByIdList(quantities.keys.toList()).associateBy { it.id }
        val orderedProducts = quantities.keys.map(products::getValue)
        for (product in orderedProducts) {
            if (quantities.getValue(product.id) > product.availability) {
                throw InsufficientStock(product.name, product.availability)
            }
        }

        for (product in orderedProducts) {
            val quantity = quantities.getValue(product.id).toInt()
            check(productDao.decreaseAvailability(product.id, quantity) == 1)
        }
        database.purchaseHistoryDao().insert(
            PurchaseHistoryEntity(
                timestamp = System.currentTimeMillis(),
                totalCost = orderedProducts.sumOf { it.pricePerUnit * quantities.getValue(it.id) },
                productIds = orderedProducts.map { it.id },
                prices = orderedProducts.map { it.pricePerUnit },
                quantities = orderedProducts.map { quantities.getValue(it.id).toInt() }
            )
        )
        cartDao.clear()
        true
    }
}
