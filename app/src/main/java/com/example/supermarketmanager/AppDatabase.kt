package com.example.supermarketmanager.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.supermarketmanager.data.dao.CategoryDao
import com.example.supermarketmanager.data.dao.ProductDao
import com.example.supermarketmanager.data.dao.ShoppingListDao
import com.example.supermarketmanager.data.dao.WishlistDao
import com.example.supermarketmanager.data.dao.PurchaseHistoryDao
import com.example.supermarketmanager.data.entities.CategoryEntity
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.data.entities.ShoppingListItemEntity
import com.example.supermarketmanager.data.entities.WishlistItemEntity
import com.example.supermarketmanager.data.entities.PurchaseHistoryEntity

@Database(
    entities = [
        CategoryEntity::class,
        ProductEntity::class,
        ShoppingListItemEntity::class,
        WishlistItemEntity::class,
        PurchaseHistoryEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun wishlistDao(): WishlistDao
    abstract fun purchaseHistoryDao(): PurchaseHistoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        /** Επιστρέφει το singleton instance της βάσης */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(appContext: Context): AppDatabase {
            return Room.databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "supermarket-db"
            )
                // .fallbackToDestructiveMigration() // αν θες απλή migration
                .build()
        }
    }
}
