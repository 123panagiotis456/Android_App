package com.example.supermarketmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity

@Dao
interface PurchaseHistoryDao {

    @Query("SELECT * FROM purchase_history ORDER BY timestamp DESC")
    suspend fun getAll(): List<PurchaseHistoryEntity>

    @Insert
    suspend fun insert(purchaseHistoryEntity: PurchaseHistoryEntity)
}
