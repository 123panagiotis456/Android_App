package com.example.supermarketmanager

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.supermarketmanager.data.entities.CategoryEntity
import kotlinx.coroutines.*

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "supermarket-db"
        )
            .fallbackToDestructiveMigration() // θα διαγράψει παλιά DB αν αλλάξει το schema
            .build()

        appScope.launch {
            val dao = database.categoryDao()
            if (dao.getAll().isEmpty()) {
                dao.insertAll(
                    listOf(
                        CategoryEntity(id = 1, name = "Fruits", imageDrawable = "ic_fruits"),
                        CategoryEntity(id = 2, name = "Vegetables", imageDrawable = "ic_vegetables"),
                        CategoryEntity(id = 3, name = "Dairy", imageDrawable = "ic_dairy"),
                        CategoryEntity(id = 4, name = "Snacks", imageDrawable = "ic_snacks"),
                        CategoryEntity(id = 5, name = "Bakery", imageDrawable = "ic_bakery")
                    )
                )
                Log.d("MyApplication", "✔ Demo κατηγορίες με εικόνες προστέθηκαν.")
            } else {
                Log.d("MyApplication", "ℹ Κατηγορίες ήδη υπάρχουν.")
            }
        }
    }
}
