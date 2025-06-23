package com.example.supermarketmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase_history")
data class PurchaseHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val shoppingListId: Int,
    val timestamp: Long,
    val totalCost: Double
)