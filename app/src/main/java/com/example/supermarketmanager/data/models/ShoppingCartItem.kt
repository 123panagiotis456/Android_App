package com.example.supermarketmanager.data.models

import androidx.room.PrimaryKey
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity


data class ShoppingCartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val productId: Int,
    val quantity: Int,
    val name: String,
    val pricePerUnit: Double,
    val unit: String,
    val imageDrawable: String?,
    val offer: String? // ✨ Βεβαιώσου ότι υπάρχει αυτό!
)




