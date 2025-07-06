package com.example.supermarketmanager.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.data.models.WishlistProductItem

@Dao
interface WishlistDao {

    @Query("SELECT * FROM wishlist_items")
    suspend fun getAll(): List<WishlistItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItem(item: WishlistItemEntity)

    @Query("DELETE FROM wishlist_items WHERE id = :itemId")
    suspend fun removeItem(itemId: Int)

    @Query("SELECT * FROM wishlist_items WHERE productId = :productId LIMIT 1")
    suspend fun getItemByProductId(productId: Int): WishlistItemEntity?
    @Query("""
    SELECT 
        wishlist_items.id AS wishlist_id,
        wishlist_items.productId AS wishlist_productId,
        products.id AS product_id,
        products.name AS product_name,
        products.description AS product_description,
        products.pricePerUnit AS product_pricePerUnit,
        products.unit AS product_unit,
        products.availability AS product_availability,
        products.offer AS product_offer,
        products.categoryId AS product_categoryId,
        products.ingredients AS product_ingredients,
        products.nutritionalInfo AS product_nutritionalInfo,
        products.imageDrawable AS product_imageDrawable
    FROM wishlist_items
    INNER JOIN products ON wishlist_items.productId = products.id
""")
    suspend fun getWishlistWithProducts(): List<WishlistProductItem>


}

