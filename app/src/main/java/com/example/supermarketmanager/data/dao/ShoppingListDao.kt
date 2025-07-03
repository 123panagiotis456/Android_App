package com.example.supermarketmanager.data.dao

import androidx.room.*
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import com.example.supermarketmanager.data.models.ShoppingCartItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {

    @Query("SELECT * FROM shopping_list")
    suspend fun getAll(): List<ShoppingListItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: ShoppingListItemEntity)

    @Update
    suspend fun update(item: ShoppingListItemEntity)

    @Delete
    suspend fun delete(item: ShoppingListItemEntity)

    @Query("DELETE FROM shopping_list")
    suspend fun clear()

    @Query(
        """
        SELECT shopping_list.*, 
               products.name, 
               products.pricePerUnit, 
               products.unit, 
               products.imageDrawable, 
               products.offer 
        FROM shopping_list
        INNER JOIN products ON shopping_list.productId = products.id
        """
    )
    fun getItemsWithProductDetails(): Flow<List<ShoppingCartItem>>

    // ✅ Προσθήκες για update ποσότητας
    @Query("SELECT * FROM shopping_list WHERE productId = :productId LIMIT 1")
    suspend fun getItemByProductId(productId: Int): ShoppingListItemEntity?

    @Query("UPDATE shopping_list SET quantity = :newQuantity WHERE productId = :productId")
    suspend fun updateQuantity(productId: Int, newQuantity: Int)
}
