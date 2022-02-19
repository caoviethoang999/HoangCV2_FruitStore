package com.example.hoangcv2_assiagnment.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.hoangcv2_assiagnment.api.ProductService
import com.example.hoangcv2_assiagnment.model.Cart
import com.example.hoangcv2_assiagnment.model.Category
import com.example.hoangcv2_assiagnment.model.Product
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import javax.inject.Inject

class ProductRepository @Inject constructor(private val productService: ProductService) {

    var productList = MutableLiveData<MutableList<Product>>()

    var categoryList = MutableLiveData<MutableList<Category>>()

    var cartList = MutableLiveData<MutableList<Cart>>()

    var errorMessage = MutableLiveData<String>()

    fun getProduct(): MutableLiveData<MutableList<Product>> {
        productService.getProduct()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MutableList<Product>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: MutableList<Product>) {
                    productList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorMessage.postValue(e.localizedMessage)
                }

                override fun onComplete() {
                }
            })
        return productList
    }

    fun getCategory(): MutableLiveData<MutableList<Category>> {
        productService.getCategory()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MutableList<Category>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: MutableList<Category>) {
                    categoryList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val errorBody = e.response()?.errorBody()?.string()
                        errorMessage.postValue(errorBody.toString())
                    }
                }

                override fun onComplete() {
                }
            })
        return categoryList
    }

    fun getCart(): MutableLiveData<MutableList<Cart>> {
        productService.getCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MutableList<Cart>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: MutableList<Cart>) {
                    cartList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    errorMessage.postValue(e.localizedMessage)
                }

                override fun onComplete() {
                }
            })
        return cartList
    }

    fun addToCart(productId:Int) {
        productService.addToCart(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Cart> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Cart) {
                }

                override fun onError(e: Throwable) {
                    errorMessage.postValue(e.localizedMessage)
                }

                override fun onComplete() {
                }
            })
    }
    fun deleteCart() {
        productService.deleteCart()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Cart> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Cart) {
                }

                override fun onError(e: Throwable) {
                    errorMessage.postValue(e.localizedMessage)
                }

                override fun onComplete() {
                }
            })
    }

    fun getProductByCategory(categoryId: Int): MutableLiveData<MutableList<Product>> {
        productService.getProductByCategory(categoryId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MutableList<Product>> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: MutableList<Product>) {
                    productList.postValue(t)
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {

                        val errorBody = e.response()?.errorBody()?.string()
                        errorMessage.postValue(errorBody.toString())
                    }
                }

                override fun onComplete() {
                }
            })
        return productList
    }

    fun handleError(): MutableLiveData<String> {
        return errorMessage
    }
}