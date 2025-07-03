package com.example.supermarketmanager.data.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u001f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J\u001b\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\t\u001a\u00020\u0006H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0007J3\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000fJ+\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0016\u001a\u00020\u0017H\u00a7@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0018\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0019"}, d2 = {"Lcom/example/supermarketmanager/data/dao/ProductDao;", "", "getByCategory", "", "Lcom/example/supermarketmanager/data/entities/ProductEntity;", "categoryId", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "productId", "getFilteredWithAvailability", "maxPrice", "", "availableOnly", "", "(Ljava/lang/Integer;Ljava/lang/Double;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFilteredWithoutAvailability", "(Ljava/lang/Integer;Ljava/lang/Double;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "", "products", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "search", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface ProductDao {
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE categoryId = :categoryId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getByCategory(int categoryId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.supermarketmanager.data.entities.ProductEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE id = :productId")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getById(int productId, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super com.example.supermarketmanager.data.entities.ProductEntity> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM products\n        WHERE (:categoryId IS NULL OR categoryId = :categoryId)\n          AND (:maxPrice IS NULL OR pricePerUnit <= :maxPrice)\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getFilteredWithoutAvailability(@org.jetbrains.annotations.Nullable
    java.lang.Integer categoryId, @org.jetbrains.annotations.Nullable
    java.lang.Double maxPrice, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.supermarketmanager.data.entities.ProductEntity>> $completion);
    
    @androidx.room.Query(value = "\n        SELECT * FROM products\n        WHERE (:categoryId IS NULL OR categoryId = :categoryId)\n          AND (:maxPrice IS NULL OR pricePerUnit <= :maxPrice)\n          AND availability = :availableOnly\n    ")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getFilteredWithAvailability(@org.jetbrains.annotations.Nullable
    java.lang.Integer categoryId, @org.jetbrains.annotations.Nullable
    java.lang.Double maxPrice, boolean availableOnly, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.supermarketmanager.data.entities.ProductEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM products WHERE name LIKE \'%\' || :search || \'%\'")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object search(@org.jetbrains.annotations.NotNull
    java.lang.String search, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.example.supermarketmanager.data.entities.ProductEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull
    java.util.List<com.example.supermarketmanager.data.entities.ProductEntity> products, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}