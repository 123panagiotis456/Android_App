package com.example.supermarketmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity

@Dao
interface ShoppingListDao {
    @Query("SELECT * FROM shopping_list_items")
    suspend fun getAll(): List<ShoppingListItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: ShoppingListItemEntity)

    @Update
    suspend fun updateItem(item: ShoppingListItemEntity)

    @Query("DELETE FROM shopping_list_items WHERE id = :itemId")
    suspend fun removeItem(itemId: Int)

    @Query("DELETE FROM shopping_list_items")
    suspend fun clearAll()
}