package com.example.supermarketmanager.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.supermarketmanager.data.entities.ProductEntity
import com.example.supermarketmanager.MyApplication
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val _products = MutableLiveData<List<ProductEntity>>()
    val products: LiveData<List<ProductEntity>> = _products

    private val _product = MutableLiveData<ProductEntity>()
    val product: LiveData<ProductEntity> = _product

    fun loadByCategory(categoryId: Int) = viewModelScope.launch {
        _products.postValue(MyApplication.database.productDao().getByCategory(categoryId))
    }

    fun loadById(productId: Int) = viewModelScope.launch {
        MyApplication.database.productDao().getById(productId)?.let {
            _product.postValue(it)
        }
    }
}
