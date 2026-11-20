package com.example.supermarketmanager.data.dao

import androidx.room.*
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import com.example.supermarketmanager.data.models.ShoppingCartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list")
    suspend fun getAll(): List<ShoppingListItemEntity>

    @Query("SELECT COALESCE(SUM(quantity), 0) FROM shopping_list WHERE productId = :productId")
    suspend fun totalQuantity(productId: Int): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingListItemEntity)

    @Update
    suspend fun update(item: ShoppingListItemEntity)

    @Query("DELETE FROM shopping_list WHERE productId = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("DELETE FROM shopping_list WHERE productId = :productId AND id != :keepId")
    suspend fun deleteDuplicates(productId: Int, keepId: Int)

    @Query("DELETE FROM shopping_list")
    suspend fun clear()

    @Query("""
    SELECT MIN(shopping_list.id) AS id, shopping_list.productId, SUM(shopping_list.quantity) AS quantity, products.name, products.pricePerUnit, products.unit, products.imageDrawable, products.offer, products.availability
    FROM shopping_list
    INNER JOIN products ON shopping_list.productId = products.id
    GROUP BY shopping_list.productId
    ORDER BY MIN(shopping_list.id)
""")
    fun getItemsWithProductDetails(): Flow<List<ShoppingCartItem>>

    @Query("SELECT * FROM shopping_list WHERE productId = :productId ORDER BY id LIMIT 1")
    suspend fun getItemByProductId(productId: Int): ShoppingListItemEntity?

}
