package com.example.hoangcv2_assiagnment.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hoangcv2_assiagnment.model.Cart

import com.example.hoangcv2_assiagnment.model.Category
import com.example.hoangcv2_assiagnment.model.Product

import javax.inject.Inject


class ProductViewModel @Inject constructor(private val mainRepository: ProductRepository) :
    ViewModel() {


    var productList = MutableLiveData<MutableList<Product>>()
    var categoryList = MutableLiveData<MutableList<Category>>()
    var cartList = MutableLiveData<MutableList<Cart>>()
    var errorMessage = mainRepository.handleError()
    fun getProduct() {
        productList = mainRepository.getProduct()
    }

    fun getCategory() {
        categoryList = mainRepository.getCategory()
    }

    fun getCart() {
        cartList = mainRepository.getCart()
    }

    fun addToCart(productId:Int) {
        mainRepository.addToCart(productId)
    }
    fun deleteCart() {
        mainRepository.deleteCart()
    }

    fun getProductByCategory(categoryId: Int) {
        productList = mainRepository.getProductByCategory(categoryId)
    }
}
