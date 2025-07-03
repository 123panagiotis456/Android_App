package com.example.supermarketmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.entities.CategoryEntity
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).categoryDao()

    private val _categories = MutableLiveData<List<CategoryEntity>>()
    val categories: LiveData<List<CategoryEntity>> = _categories

    private var fullList: List<CategoryEntity> = emptyList() // κρατάμε όλες για το search

    /** Φόρτωση όλων των κατηγοριών (αρχικά) */
    fun loadAllCategories() {
        viewModelScope.launch {
            fullList = dao.getAll()
            _categories.value = fullList
        }
    }

    /** Αναζήτηση με φίλτρο */
    fun searchCategories(query: String) {
        val filtered = if (query.isBlank()) {
            fullList
        } else {
            fullList.filter { it.name.contains(query, ignoreCase = true) }
        }
        _categories.value = filtered
    }
}


