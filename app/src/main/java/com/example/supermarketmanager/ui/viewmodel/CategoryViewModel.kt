package com.example.supermarketmanager.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.AppDatabase
import com.example.supermarketmanager.data.entities.CategoryEntity
import kotlinx.coroutines.launch
import android.util.Log

class CategoryViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = AppDatabase.getInstance(application).categoryDao()

    private val _categories = MutableLiveData<List<CategoryEntity>>()
    val categories: LiveData<List<CategoryEntity>> = _categories

    private var fullList: List<CategoryEntity> = emptyList()

    fun loadAllCategories() {
        viewModelScope.launch {
            try {
                fullList = dao.getAll()
                _categories.value = fullList
            } catch (e: Exception) {
                Log.e("CategoryViewModel", "Error loading categories", e)
                _categories.value = emptyList()
            }
        }
    }

    fun searchCategories(query: String) {
        try {
            val filtered = if (query.isBlank()) {
                fullList
            } else {
                fullList.filter { it.name.contains(query, ignoreCase = true) }
            }
            _categories.value = filtered
        } catch (e: Exception) {
            Log.e("CategoryViewModel", "Error searching categories", e)
            _categories.value = emptyList()
        }
    }
}
