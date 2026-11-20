package com.example.supermarketmanager.data.repository

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.entities.CategoryEntity
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CartRepositoryTest {
    private lateinit var database: AppDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun purchaseUpdatesInventoryHistoryAndCart() = runBlocking {
        seedCart(secondQuantity = 2)

        assertTrue(CartRepository.makePurchase(database))

        assertEquals(3, stock(1))
        assertEquals(3, stock(2))
        val purchase = database.purchaseHistoryDao().getAll().single()
        assertEquals(mapOf(1 to 2, 2 to 2), purchase.productIds.zip(purchase.quantities).toMap())
        assertEquals(10.0, purchase.totalCost, 0.0)
        assertTrue(database.shoppingListDao().getAll().isEmpty())
    }

    @Test
    fun insufficientStockKeepsCartInventoryAndHistoryIntact() = runBlocking {
        seedCart(secondQuantity = 4)
        database.shoppingListDao().insert(ShoppingListItemEntity(productId = 2, quantity = 2))
        val cart = database.shoppingListDao().getAll()
        val error = runCatching { CartRepository.makePurchase(database) }.exceptionOrNull()

        assertTrue(error is CartRepository.InsufficientStock)
        error as CartRepository.InsufficientStock
        assertEquals("Bread", error.productName)
        assertEquals(5, error.available)
        assertEquals(5, stock(1))
        assertEquals(5, stock(2))
        assertEquals(cart, database.shoppingListDao().getAll())
        assertTrue(database.purchaseHistoryDao().getAll().isEmpty())
    }

    @Test
    fun simultaneousOrdersRecordOnePurchase() = runBlocking {
        seedCart(secondQuantity = 2)

        val results = listOf(
            async { CartRepository.makePurchase(database) },
            async { CartRepository.makePurchase(database) }
        ).awaitAll()

        assertEquals(1, results.count { it })
        assertEquals(1, database.purchaseHistoryDao().getAll().size)
        assertEquals(3, stock(1))
        assertEquals(3, stock(2))
    }

    @Test
    fun simultaneousQuantityChangesUseTheLatestQuantity() = runBlocking {
        seedCart(secondQuantity = 2)

        listOf(
            async { CartRepository.adjustQuantity(database, 1, 1) },
            async { CartRepository.adjustQuantity(database, 1, 1) }
        ).awaitAll()
        assertEquals(4, database.shoppingListDao().getItemByProductId(1)?.quantity)

        repeat(4) { CartRepository.adjustQuantity(database, 1, -1, minimum = 1) }
        assertEquals(1, database.shoppingListDao().getItemByProductId(1)?.quantity)

        CartRepository.adjustQuantity(database, 1, -1)
        assertTrue(database.shoppingListDao().getItemByProductId(1) == null)
    }

    private suspend fun seedCart(secondQuantity: Int) {
        database.categoryDao().insertAll(listOf(CategoryEntity(1, "Food")))
        database.productDao().insertAll(listOf(product(1, "Apple", 2.0), product(2, "Bread", 3.0)))
        database.shoppingListDao().insert(ShoppingListItemEntity(productId = 1, quantity = 2))
        database.shoppingListDao().insert(ShoppingListItemEntity(productId = 2, quantity = secondQuantity))
    }

    private fun product(id: Int, name: String, price: Double) = ProductEntity(
        id = id,
        name = name,
        description = "",
        pricePerUnit = price,
        unit = "item",
        availability = 5,
        offer = null,
        categoryId = 1,
        ingredients = null,
        nutritionalInfo = null,
        imageDrawable = null
    )

    private suspend fun stock(productId: Int) =
        requireNotNull(database.productDao().getById(productId)).availability
}
