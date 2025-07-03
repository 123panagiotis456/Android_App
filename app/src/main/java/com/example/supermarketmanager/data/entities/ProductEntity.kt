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
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val pricePerUnit: Double,
    val unit: String,
    val availability: Int,
    val offer: String?, // π.χ. "1+1 Δώρο"
    val categoryId: Int,
    val ingredients: String?,
    val nutritionalInfo: String?,
    val imageDrawable: String? // π.χ. "wine_image"
)
