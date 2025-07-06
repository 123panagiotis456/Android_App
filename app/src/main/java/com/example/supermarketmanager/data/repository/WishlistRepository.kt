package com.example.supermarketmanager.data.repository

import com.example.supermarketmanager.data.dao.WishlistDao
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.data.models.WishlistProductItem

class WishlistRepository(private val wishlistDao: WishlistDao) {

    suspend fun isFavorite(productId: Int): Boolean {
        return wishlistDao.getItemByProductId(productId) != null
    }

    suspend fun addToWishlist(productId: Int) {
        val item = WishlistItemEntity(productId = productId)
        wishlistDao.addItem(item)
    }

    suspend fun removeFromWishlist(productId: Int) {
        val item = wishlistDao.getItemByProductId(productId)
        item?.let {
            wishlistDao.removeItem(it.id)
        }
    }

    suspend fun removeFromWishlistById(itemId: Int) {
        wishlistDao.removeItem(itemId)
    }

    suspend fun getAllFavorites(): List<WishlistItemEntity> {
        return wishlistDao.getAll()
    }

    // **ΑΥΤΗ ΕΙΝΑΙ Η ΣΥΝΑΡΤΗΣΗ ΠΟΥ ΧΡΕΙΑΖΕΣΑΙ**
    suspend fun getWishlistWithProducts(): List<WishlistProductItem> {
        return wishlistDao.getWishlistWithProducts()
    }
}
