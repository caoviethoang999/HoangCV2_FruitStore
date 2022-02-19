package com.example.hoangcv2_assiagnment.api

import com.example.hoangcv2_assiagnment.model.Cart
import com.example.hoangcv2_assiagnment.model.Category
import com.example.hoangcv2_assiagnment.model.Product
import io.reactivex.Observable
import retrofit2.http.*

interface ProductService {
    @GET("/HoangCV2/displayProduct.php")
    fun getProduct(): Observable<MutableList<Product>>

    @GET("/HoangCV2/displayCategory.php")
    fun getCategory(): Observable<MutableList<Category>>

    @GET("/HoangCV2/displayCart.php")
    fun getCart(): Observable<MutableList<Cart>>

    @POST("/HoangCV2/addToCart.php")
    @FormUrlEncoded
    fun addToCart(@Field("product_id") productId: Int): Observable<Cart>

    @DELETE("/HoangCV2/deleteCart.php")
    fun deleteCart(): Observable<Cart>

    @POST("/HoangCV2/displayProductByCategory.php")
    @FormUrlEncoded
    fun getProductByCategory(@Field("category_id") categoryId: Int): Observable<MutableList<Product>>

    @POST("/HoangCV2/deleteProduct.php")
    @FormUrlEncoded
    fun deleteProduct(
        @Field("product_id") productId: Int,
    ): Observable<Product>
}