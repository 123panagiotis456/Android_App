package com.example.supermarketmanager.data.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "products",
    foreignKeys = [ForeignKey(
        entity = CategoryEntity::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index("categoryId")]
)
data class ProductEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val pricePerUnit: Double,
    val unit: String,
    val imageUrl: String,
    val availability: Boolean,
    val offer: String?,
    val categoryId: Int,
    val ingredients: String?,
    val nutritionalInfo: String?
)