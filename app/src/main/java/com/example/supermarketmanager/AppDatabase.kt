package com.example.supermarketmanager

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.supermarketmanager.data.dao.*
import com.example.supermarketmanager.data.entities.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        CategoryEntity::class,
        ProductEntity::class,
        ShoppingListItemEntity::class,
        WishlistItemEntity::class,
        PurchaseHistoryEntity::class
    ],
    version = 5,
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
                .fallbackToDestructiveMigration()
                .addCallback(object : Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)

                        CoroutineScope(Dispatchers.IO).launch {
                            val database = getInstance(appContext)
                            val categoryDao = database.categoryDao()
                            val productDao = database.productDao()
                            val shoppingListDao = database.shoppingListDao()
                            val wishlistDao = database.wishlistDao()

                            if (categoryDao.getAll().isEmpty()) {
                                categoryDao.insertAll(
                                    listOf(
                                        CategoryEntity(1, "Fruits"),
                                        CategoryEntity(2, "Vegetables"),
                                        CategoryEntity(3, "Dairy"),
                                        CategoryEntity(4, "Snacks"),
                                        CategoryEntity(5, "Bakery")
                                    )
                                )
                            }

                            if (productDao.getById(1) == null) {
                                productDao.insertAll(
                                    listOf(
                                        ProductEntity(
                                            id = 1,
                                            name = "Banana",
                                            description = "Fresh bananas from Ecuador",
                                            pricePerUnit = 3.99,
                                            unit = "€/kg",
                                            availability = 10,
                                            offer = "1+1",
                                            categoryId = 1,
                                            ingredients = "100% φρέσκες μπανάνες",
                                            nutritionalInfo = """
                                                Θρεπτική αξία ανά 100 g:
                                                • Ενέργεια: 89 kcal
                                                • Λιπαρά: 0.3 g
                                                • Υδατάνθρακες: 22.8 g
                                                • Πρωτεΐνη: 1.1 g
                                                • Φυτικές Ίνες: 2.6 g
                                                • Σάκχαρα: 12.2 g
                                                • Κάλιο: 358 mg
                                                • Βιταμίνη C: 8.7 mg
                                            """.trimIndent(),
                                            imageDrawable = "banana_svgrepo_com"
                                        ),
                                        ProductEntity(
                                            id = 4,
                                            name = "Apple",
                                            description = "Φρέσκα μήλα από τον Ισημερινό",
                                            pricePerUnit = 2.99,
                                            unit = "€/kg",
                                            availability = 10,
                                            offer = "1+1",
                                            categoryId = 1,
                                            ingredients = "100% φρέσκα μήλα",
                                            nutritionalInfo = """
                                                Θρεπτική αξία ανά 100 g:
                                                • Ενέργεια: 52 kcal
                                                • Λιπαρά: 0.2 g
                                                • Υδατάνθρακες: 13.8 g
                                                • Πρωτεΐνη: 0.3 g
                                                • Φυτικές Ίνες: 2.4 g
                                                • Σάκχαρα: 10.4 g
                                                • Κάλιο: 107 mg
                                                • Βιταμίνη C: 4.6 mg
                                            """.trimIndent(),
                                            imageDrawable = "apple_svgrepo_com"
                                        ),
                                        ProductEntity(
                                            id = 2,
                                            name = "Milk 1L",
                                            description = "Greek cow milk, full fat",
                                            pricePerUnit = 1.29,
                                            unit = "€/item",
                                            availability = 10,
                                            offer = "10% off",
                                            categoryId = 3,
                                            ingredients = "100% ελληνικό πλήρες γάλα αγελάδος",
                                            nutritionalInfo = """
                                                Θρεπτική αξία ανά 100 ml:
                                                • Ενέργεια: 64 kcal
                                                • Λιπαρά: 3.5 g
                                                • εκ των οποίων κορεσμένα: 2.3 g
                                                • Υδατάνθρακες: 4.8 g
                                                • εκ των οποίων σάκχαρα: 4.8 g
                                                • Πρωτεΐνη: 3.3 g
                                                • Αλάτι: 0.10 g
                                                • Ασβέστιο: 120 mg
                                            """.trimIndent(),
                                            imageDrawable = "milk_carton_svgrepo_com"
                                        ),
                                        ProductEntity(
                                            id = 3,
                                            name = "Bread",
                                            description = "Φρέσκο λευκό ψωμί",
                                            pricePerUnit = 0.80,
                                            unit = "€/item",
                                            availability = 10,
                                            offer = "20% off",
                                            categoryId = 5,
                                            ingredients = "Αλεύρι σίτου, νερό, μαγιά, αλάτι",
                                            nutritionalInfo = """
                                                Θρεπτική αξία ανά 100 g:
                                                • Ενέργεια: 266 kcal
                                                • Λιπαρά: 3.2 g
                                                • εκ των οποίων κορεσμένα: 0.6 g
                                                • Υδατάνθρακες: 49.4 g
                                                • εκ των οποίων σάκχαρα: 1.6 g
                                                • Πρωτεΐνη: 8.3 g
                                                • Φυτικές Ίνες: 2.7 g
                                                • Αλάτι: 1.2 g
                                            """.trimIndent(),
                                            imageDrawable = "bread_svgrepo_com"
                                        )
                                    )
                                )

                                // ✅ Mock καλάθι
                                if (shoppingListDao.getAll().isEmpty()) {
                                    shoppingListDao.insert(ShoppingListItemEntity(productId = 1, quantity = 2))
                                    shoppingListDao.insert(ShoppingListItemEntity(productId = 2, quantity = 1))
                                    shoppingListDao.insert(ShoppingListItemEntity(productId = 3, quantity = 8))
                                }

                                // ✅ Mock αγαπημένα
                                if (wishlistDao.getAll().isEmpty()) {
                                    wishlistDao.addItem(WishlistItemEntity(productId = 1)) // Banana
                                    wishlistDao.addItem(WishlistItemEntity(productId = 4)) // Apple
                                }
                            }

                            Log.d("DBG", "Mock δεδομένα εισήχθησαν με επιτυχία!")
                        }
                    }
                })
                .build()
        }
    }
}
