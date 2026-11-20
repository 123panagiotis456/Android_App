package com.example.supermarketmanager.data.repository

import androidx.room.withTransaction
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.dao.ShoppingListDao
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity

object CartRepository {
    fun observeItems(database: AppDatabase) =
        database.shoppingListDao().getItemsWithProductDetails()

    suspend fun clear(database: AppDatabase) = database.shoppingListDao().clear()

    suspend fun setQuantity(database: AppDatabase, productId: Int, quantity: Int) {
        database.withTransaction {
            database.shoppingListDao().changeQuantity(productId) { quantity }
        }
    }

    suspend fun adjustQuantity(database: AppDatabase, productId: Int, delta: Int, minimum: Int = 0) {
        database.withTransaction {
            database.shoppingListDao().changeQuantity(productId) { current ->
                if (current == 0 && delta < 0) 0 else (current + delta).coerceAtLeast(minimum)
            }
        }
    }

    suspend fun readdPurchase(database: AppDatabase, purchase: PurchaseHistoryEntity) {
        database.withTransaction {
            val dao = database.shoppingListDao()
            purchase.productIds.forEachIndexed { index, productId ->
                val quantity = purchase.quantities[index]
                dao.changeQuantity(productId) { it + quantity }
            }
        }
    }

    private suspend fun ShoppingListDao.changeQuantity(
        productId: Int,
        newQuantity: (Int) -> Int
    ) {
        val item = getItemByProductId(productId)
        val quantity = newQuantity(item?.quantity ?: 0)
        when {
            quantity <= 0 -> item?.let { delete(it) }
            item == null -> insert(ShoppingListItemEntity(productId = productId, quantity = quantity))
            quantity != item.quantity -> update(item.copy(quantity = quantity))
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
