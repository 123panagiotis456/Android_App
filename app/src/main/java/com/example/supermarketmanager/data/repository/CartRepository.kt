package com.example.supermarketmanager.data.repository

import androidx.room.withTransaction
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.CartPricing
import com.example.supermarketmanager.data.dao.ShoppingListDao
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import org.json.JSONArray

object CartRepository {
    fun observeItems(database: AppDatabase) =
        database.shoppingListDao().getItemsWithProductDetails()

    suspend fun clear(database: AppDatabase) = database.shoppingListDao().clear()

    suspend fun setQuantity(database: AppDatabase, productId: Int, quantity: Int): Boolean =
        updateQuantity(database, productId) { quantity.toLong() }

    suspend fun adjustQuantity(
        database: AppDatabase, productId: Int, delta: Int, minimum: Int = 0
    ): Boolean = updateQuantity(database, productId) { current ->
        if (current == 0L && delta < 0) 0L else (current + delta).coerceAtLeast(minimum.toLong())
    }

    suspend fun addOneIfAvailable(database: AppDatabase, productId: Int): Boolean =
        adjustQuantity(database, productId, 1)

    private suspend fun updateQuantity(
        database: AppDatabase, productId: Int, newQuantity: (Long) -> Long
    ): Boolean = database.withTransaction {
        val dao = database.shoppingListDao()
        val current = dao.totalQuantity(productId)
        val desired = newQuantity(current).coerceAtLeast(0)
        if (desired == current) return@withTransaction true
        if (desired > current) {
            val product = database.productDao().getById(productId) ?: return@withTransaction false
            if (desired > product.availability) return@withTransaction false
        }
        if (desired == 0L) {
            dao.deleteProduct(productId)
        } else {
            val quantity = desired.toInt()
            val item = dao.getItemByProductId(productId)
            if (item == null) {
                dao.insert(ShoppingListItemEntity(productId = productId, quantity = quantity))
            } else {
                dao.update(item.copy(quantity = quantity))
                dao.deleteDuplicates(productId, item.id)
            }
        }
        true
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
        val lineCents = orderedProducts.map {
            CartPricing.lineCents(it.pricePerUnit, quantities.getValue(it.id).toInt(), it.offer)
        }
        database.purchaseHistoryDao().insert(
            PurchaseHistoryEntity(
                timestamp = System.currentTimeMillis(),
                totalCost = lineCents.sum() / 100.0,
                productIds = orderedProducts.map { it.id },
                prices = orderedProducts.map { it.pricePerUnit },
                quantities = orderedProducts.map { quantities.getValue(it.id).toInt() },
                productNamesJson = JSONArray(orderedProducts.map { it.name }).toString(),
                lineTotals = lineCents.map { it / 100.0 }
            )
        )
        cartDao.clear()
        true
    }
}
