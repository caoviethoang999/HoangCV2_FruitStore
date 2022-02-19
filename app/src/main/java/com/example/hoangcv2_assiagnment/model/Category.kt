package com.example.hoangcv2_assiagnment.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    var categoryId: Int,
    @SerializedName("category_name")
    var categoryName: String,
    @SerializedName("category_image")
    var categoryImage: String,
    @SerializedName("category_background")
    var categoryBackground: String
) : Serializable
