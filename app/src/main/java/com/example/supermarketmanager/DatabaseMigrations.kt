package com.example.supermarketmanager

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

internal val MIGRATION_1_6 = object : Migration(1, 6) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALTER TABLE categories ADD COLUMN imageDrawable TEXT")
        db.execSQL("CREATE TEMP TABLE cart_backup AS SELECT * FROM shopping_list_items")
        db.execSQL("CREATE TEMP TABLE wishlist_backup AS SELECT * FROM wishlist_items")
        db.execSQL("DROP TABLE shopping_list_items")
        db.execSQL("DROP TABLE wishlist_items")

        db.execSQL(
            """
            CREATE TABLE products_new (
                id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                name TEXT NOT NULL, description TEXT NOT NULL, pricePerUnit REAL NOT NULL,
                unit TEXT NOT NULL, availability INTEGER NOT NULL, offer TEXT,
                categoryId INTEGER NOT NULL, ingredients TEXT, nutritionalInfo TEXT, imageDrawable TEXT,
                FOREIGN KEY(categoryId) REFERENCES categories(id) ON DELETE CASCADE
            )
            """.trimIndent()
        )
        db.execSQL(
            """
            INSERT INTO products_new
                (id, name, description, pricePerUnit, unit, availability, offer,
                 categoryId, ingredients, nutritionalInfo)
            SELECT id, name, description, pricePerUnit, unit, availability, offer,
                   categoryId, ingredients, nutritionalInfo
            FROM products
            """.trimIndent()
        )
        db.execSQL("DROP TABLE products")
        db.execSQL("ALTER TABLE products_new RENAME TO products")
        db.execSQL("CREATE INDEX index_products_categoryId ON products(categoryId)")

        for ((table, backup, extraColumn) in listOf(
            Triple("shopping_list", "cart_backup", ", quantity INTEGER NOT NULL"),
            Triple("wishlist_items", "wishlist_backup", "")
        )) {
            db.execSQL(
                """
                CREATE TABLE $table (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    productId INTEGER NOT NULL$extraColumn,
                    FOREIGN KEY(productId) REFERENCES products(id) ON DELETE CASCADE
                )
                """.trimIndent()
            )
            db.execSQL("INSERT INTO $table SELECT * FROM $backup")
            db.execSQL("DROP TABLE $backup")
            db.execSQL("CREATE INDEX index_${table}_productId ON $table(productId)")
        }

        migrateLegacyPurchaseHistory(db)
    }
}

internal val MIGRATION_5_6 = object : Migration(5, 6) {
    override fun migrate(db: SupportSQLiteDatabase) {
        // Older builds used version 5 for a different purchase history schema.
        val legacyHistory = db.query("PRAGMA table_info(purchase_history)").use { columns ->
            val nameIndex = columns.getColumnIndexOrThrow("name")
            var found = false
            while (columns.moveToNext()) {
                if (columns.getString(nameIndex) == "shoppingListId") {
                    found = true
                    break
                }
            }
            found
        }
        if (legacyHistory) migrateLegacyPurchaseHistory(db)
    }
}

private fun migrateLegacyPurchaseHistory(db: SupportSQLiteDatabase) {
    db.execSQL(
        """
        CREATE TABLE purchase_history_new (
            id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
            timestamp INTEGER NOT NULL, totalCost REAL NOT NULL,
            productIds TEXT NOT NULL, prices TEXT NOT NULL, quantities TEXT NOT NULL
        )
        """.trimIndent()
    )
    // Legacy purchases stored totals and dates but had no snapshot of the purchased items.
    db.execSQL(
        """
        INSERT INTO purchase_history_new (id, timestamp, totalCost, productIds, prices, quantities)
        SELECT id, timestamp, totalCost, '', '', ''
        FROM purchase_history
        """.trimIndent()
    )
    db.execSQL("DROP TABLE purchase_history")
    db.execSQL("ALTER TABLE purchase_history_new RENAME TO purchase_history")
}
