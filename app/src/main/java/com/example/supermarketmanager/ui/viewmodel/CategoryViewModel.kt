package com.example.supermarketmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.AppDatabase
import com.example.supermarketmanager.data.entities.CategoryEntity
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    // Παίρνουμε το singleton instance της βάσης και το DAO
    private val dao = AppDatabase.getInstance(application).categoryDao()

    // Backing LiveData για τις κατηγορίες
    private val _categories = MutableLiveData<List<CategoryEntity>>()
    val categories: LiveData<List<CategoryEntity>> = _categories

    /** Καλείται από το Fragment για να φορτώσει τις κατηγορίες */
    fun loadAllCategories() {
        viewModelScope.launch {
            // Καλούμε ακριβώς τη μέθοδο του DAO
            val list = dao.getAll()
            _categories.value = list
        }
    }
}

