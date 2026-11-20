package com.example.supermarketmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.supermarketmanager.data.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    suspend fun getByCategory(categoryId: Int): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getById(productId: Int): ProductEntity?

    @Query("SELECT * FROM products WHERE id = :productId")
    fun observeById(productId: Int): Flow<ProductEntity?>

    @Query("""
        SELECT * FROM products
        WHERE (:categoryId < 0 OR categoryId = :categoryId)
          AND (:availableOnly = 0 OR availability > 0)
        ORDER BY id
    """)
    fun observeCatalog(
        categoryId: Int, availableOnly: Boolean
    ): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM products WHERE id IN (:ids)")
    suspend fun getByIdList(ids: List<Int>): List<ProductEntity>

    @Query("UPDATE products SET availability = availability - :quantity WHERE id = :productId AND availability >= :quantity")
    suspend fun decreaseAvailability(productId: Int, quantity: Int): Int
}
