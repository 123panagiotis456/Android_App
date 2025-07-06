package com.example.supermarketmanager.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u001b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000b\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rJ\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\bH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\tJ\u0019\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\r\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/supermarketmanager/data/dao/WishlistDao;", "", "addItem", "", "item", "Lcom/example/supermarketmanager/data/entities/WishlistItemEntity;", "(Lcom/example/supermarketmanager/data/entities/WishlistItemEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAll", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getItemByProductId", "productId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getWishlistWithProducts", "Lcom/example/supermarketmanager/data/models/WishlistProductItem;", "removeItem", "itemId", "app_debug"})
@androidx.room.Dao
public abstract interface WishlistDao {
    
    @androidx.room.Query(value = "SELECT * FROM wishlist_items")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getAll(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.supermarketmanager.data.entities.WishlistItemEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object addItem(@org.jetbrains.annotations.NotNull
    com.example.supermarketmanager.data.entities.WishlistItemEntity item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM wishlist_items WHERE id = :itemId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object removeItem(int itemId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM wishlist_items WHERE productId = :productId LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getItemByProductId(int productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.supermarketmanager.data.entities.WishlistItemEntity> $completion);
    
    @androidx.room.Query(value = "\n    SELECT \n        wishlist_items.id AS wishlist_id,\n        wishlist_items.productId AS wishlist_productId,\n        products.id AS product_id,\n        products.name AS product_name,\n        products.description AS product_description,\n        products.pricePerUnit AS product_pricePerUnit,\n        products.unit AS product_unit,\n        products.availability AS product_availability,\n        products.offer AS product_offer,\n        products.categoryId AS product_categoryId,\n        products.ingredients AS product_ingredients,\n        products.nutritionalInfo AS product_nutritionalInfo,\n        products.imageDrawable AS product_imageDrawable\n    FROM wishlist_items\n    INNER JOIN products ON wishlist_items.productId = products.id\n")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getWishlistWithProducts(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.supermarketmanager.data.models.WishlistProductItem>> $completion);
}