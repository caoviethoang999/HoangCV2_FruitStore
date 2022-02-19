package com.example.hoangcv2_assiagnment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("product_id")
    var productId: Int,
    @SerializedName("product_name")
    var productName: String,
    @SerializedName("product_des")
    var productDes: String,
    @SerializedName("product_quantity")
    var productQuantity: Int,
    @SerializedName("product_price")
    var productPrice: Double,
    @SerializedName("product_image")
    var productImage: String,
    @SerializedName("product_background")
    var productBackground: String,
    @SerializedName("is_favorite")
    var isFavorite: Boolean,
) : Serializable

abstract class ProductCart(
    open var productName: String,
    open var productPrice: Double,
    open var productImage: String,
    open var productBackground: String,
    open var isFavorite: Boolean
    )