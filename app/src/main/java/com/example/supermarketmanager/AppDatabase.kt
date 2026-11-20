package com.example.supermarketmanager

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.supermarketmanager.data.dao.*
import com.example.supermarketmanager.data.seedDemoData
import com.example.supermarketmanager.data.entities.*

@Database(
    entities = [
        CategoryEntity::class,
        ProductEntity::class,
        ShoppingListItemEntity::class,
        WishlistItemEntity::class,
        PurchaseHistoryEntity::class
    ],
    version = 6,
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
                .addMigrations(MIGRATION_1_6, MIGRATION_5_6)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        seedDemoData(db)
                    }
                })
                .build()
        }
    }
}
