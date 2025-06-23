package com.example.supermarketmanager

import android.app.Application
import androidx.room.Room
import com.example.supermarketmanager.data.AppDatabase
import com.example.supermarketmanager.data.entities.CategoryEntity
import kotlinx.coroutines.*

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    // 🔹 Εδώ βάζεις το δικό σου scope για background λειτουργίες
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "app_database"
        )
            .fallbackToDestructiveMigration()
            .build()

        // 🔹 Προσθήκη demo δεδομένων (μόνο αν λείπουν)
        appScope.launch {
            val dao = database.categoryDao()
            if (dao.getAll().isEmpty()) {
                dao.insertAll(
                    listOf(
                        CategoryEntity(id = 0, name = "Fruits"),
                        CategoryEntity(id = 0, name = "Vegetables"),
                        CategoryEntity(id = 0, name = "Dairy")
                    )
                )
            }
        }
    }
}


