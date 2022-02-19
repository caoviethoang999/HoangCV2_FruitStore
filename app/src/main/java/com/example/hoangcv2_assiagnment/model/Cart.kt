package com.example.hoangcv2_assiagnment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cart(
    @SerializedName("cart_id")
    var cartId:Int,
    @SerializedName("product_name")
    override var productName: String,
    @SerializedName("product_price")
    override var productPrice: Double,
    @SerializedName("product_image")
    override var productImage: String,
    @SerializedName("product_background")
    override var productBackground: String,
    @SerializedName("is_favorite")
    override var isFavorite: Boolean,
):ProductCart(productName,productPrice,productImage,productBackground,isFavorite),Serializable