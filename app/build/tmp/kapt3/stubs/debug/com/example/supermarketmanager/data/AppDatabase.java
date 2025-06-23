package com.example.supermarketmanager.data;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH&J\b\u0010\t\u001a\u00020\nH&J\b\u0010\u000b\u001a\u00020\fH&\u00a8\u0006\u000e"}, d2 = {"Lcom/example/supermarketmanager/data/AppDatabase;", "Landroidx/room/RoomDatabase;", "()V", "categoryDao", "Lcom/example/supermarketmanager/data/dao/CategoryDao;", "productDao", "Lcom/example/supermarketmanager/data/dao/ProductDao;", "purchaseHistoryDao", "Lcom/example/supermarketmanager/data/dao/PurchaseHistoryDao;", "shoppingListDao", "Lcom/example/supermarketmanager/data/dao/ShoppingListDao;", "wishlistDao", "Lcom/example/supermarketmanager/data/dao/WishlistDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.example.supermarketmanager.data.entities.CategoryEntity.class, com.example.supermarketmanager.data.entities.ProductEntity.class, com.example.supermarketmanager.data.entities.ShoppingListItemEntity.class, com.example.supermarketmanager.data.entities.WishlistItemEntity.class, com.example.supermarketmanager.data.entities.PurchaseHistoryEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile
    @org.jetbrains.annotations.Nullable
    private static volatile com.example.supermarketmanager.data.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull
    public static final com.example.supermarketmanager.data.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.supermarketmanager.data.dao.CategoryDao categoryDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.supermarketmanager.data.dao.ProductDao productDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.supermarketmanager.data.dao.ShoppingListDao shoppingListDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.supermarketmanager.data.dao.WishlistDao wishlistDao();
    
    @org.jetbrains.annotations.NotNull
    public abstract com.example.supermarketmanager.data.dao.PurchaseHistoryDao purchaseHistoryDao();
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/example/supermarketmanager/data/AppDatabase$Companion;", "", "()V", "INSTANCE", "Lcom/example/supermarketmanager/data/AppDatabase;", "buildDatabase", "appContext", "Landroid/content/Context;", "getInstance", "context", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        /**
         * Επιστρέφει το singleton instance της βάσης
         */
        @org.jetbrains.annotations.NotNull
        public final com.example.supermarketmanager.data.AppDatabase getInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
        
        private final com.example.supermarketmanager.data.AppDatabase buildDatabase(android.content.Context appContext) {
            return null;
        }
    }
}