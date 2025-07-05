package com.example.supermarketmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.supermarketmanager.data.entities.ProductEntity

@Dao
interface ProductDao {

    @Query("SELECT * FROM products WHERE categoryId = :categoryId")
    suspend fun getByCategory(categoryId: Int): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getById(productId: Int): ProductEntity?

    @Query("""
        SELECT * FROM products
        WHERE (:categoryId IS NULL OR categoryId = :categoryId)
          AND (:maxPrice IS NULL OR pricePerUnit <= :maxPrice)
    """)
    suspend fun getFilteredWithoutAvailability(
        categoryId: Int?,
        maxPrice: Double?
    ): List<ProductEntity>

    @Query("""
        SELECT * FROM products
        WHERE (:categoryId IS NULL OR categoryId = :categoryId)
          AND (:maxPrice IS NULL OR pricePerUnit <= :maxPrice)
          AND availability = :availableOnly
    """)
    suspend fun getFilteredWithAvailability(
        categoryId: Int?,
        maxPrice: Double?,
        availableOnly: Boolean
    ): List<ProductEntity>

    @Query("SELECT * FROM products WHERE name LIKE '%' || :search || '%'")
    suspend fun search(search: String): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)

    @Query("SELECT * FROM products WHERE id IN (:ids)")
    suspend fun getByIdList(ids: List<Int>): List<ProductEntity>



}
