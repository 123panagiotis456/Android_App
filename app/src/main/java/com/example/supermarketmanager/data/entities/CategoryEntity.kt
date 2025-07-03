package com.example.supermarketmanager.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey val id: Int, // Χειροκίνητο ID
    val name: String,
    val imageDrawable: String? = null // π.χ. "ic_fruits"
)
