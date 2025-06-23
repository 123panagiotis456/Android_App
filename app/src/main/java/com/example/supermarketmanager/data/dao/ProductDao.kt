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

    @Query("SELECT * FROM products WHERE name LIKE '%' || :search || '%' OR offer IS NOT NULL AND offer LIKE '%' || :search || '%'")
    suspend fun search(search: String): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductEntity>)
}