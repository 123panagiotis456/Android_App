package com.example.supermarketmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity

@Dao
interface PurchaseHistoryDao {
    @Query("SELECT * FROM purchase_history")
    suspend fun getAll(): List<PurchaseHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHistory(history: PurchaseHistoryEntity)
}