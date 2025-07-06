package com.example.supermarketmanager.data.models

import androidx.room.Embedded
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.data.entities.ProductEntity

data class WishlistProductItem(
    @Embedded(prefix = "wishlist_") val wishlist: WishlistItemEntity,
    @Embedded(prefix = "product_") val product: ProductEntity
)
