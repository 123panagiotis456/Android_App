package com.example.supermarketmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.supermarketmanager.data.entities.WishlistItemEntity

@Dao
interface WishlistDao {
    @Query("SELECT * FROM wishlist_items")
    suspend fun getAll(): List<WishlistItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: WishlistItemEntity)

    @Query("DELETE FROM wishlist_items WHERE id = :itemId")
    suspend fun removeItem(itemId: Int)
}