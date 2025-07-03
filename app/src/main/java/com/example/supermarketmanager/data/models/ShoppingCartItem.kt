package com.example.supermarketmanager.data.models

import com.example.supermarketmanager.data.entities.ShoppingListItemEntity


data class ShoppingCartItem(
    val id: Int,
    val productId: Int,
    val quantity: Int,
    val name: String,
    val pricePerUnit: Double,
    val unit: String,
    val imageDrawable: String?,
    val offer: String? // ✨ Βεβαιώσου ότι υπάρχει αυτό!
)




