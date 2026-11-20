package com.example.supermarketmanager.data

import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.supermarketmanager.data.entities.CategoryEntity
import com.example.supermarketmanager.data.entities.ProductEntity

internal fun seedDemoData(database: SupportSQLiteDatabase) {
    for (category in demoCategories) {
        database.execSQL(
            "INSERT INTO categories (id, name, imageDrawable) VALUES (?, ?, ?)",
            arrayOf(category.id, category.name, category.imageDrawable)
        )
    }
    for (product in demoProducts) {
        database.execSQL(
            """
            INSERT INTO products
                (id, name, description, pricePerUnit, unit, availability, offer, categoryId,
                 ingredients, nutritionalInfo, imageDrawable)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """.trimIndent(),
            arrayOf(
                product.id, product.name, product.description, product.pricePerUnit, product.unit,
                product.availability, product.offer, product.categoryId, product.ingredients,
                product.nutritionalInfo, product.imageDrawable
            )
        )
    }

    database.execSQL("INSERT INTO shopping_list (productId, quantity) VALUES (1, 2), (2, 1), (3, 8)")
    database.execSQL("INSERT INTO wishlist_items (productId) VALUES (1), (4)")
}

private val demoCategories = listOf(
    CategoryEntity(1, "Fruits", "ic_fruits"),
    CategoryEntity(2, "Vegetables", "ic_vegetables"),
    CategoryEntity(3, "Dairy", "ic_dairy"),
    CategoryEntity(4, "Snacks", "ic_snacks"),
    CategoryEntity(5, "Bakery", "ic_bakery")
)

private val demoProducts = listOf(
    ProductEntity(
        id = 1,
        name = "Banana",
        description = "Fresh bananas from Ecuador",
        pricePerUnit = 3.99,
        unit = "€/kg",
        availability = 200,
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
        availability = 200,
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
        availability = 200,
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
        availability = 200,
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
