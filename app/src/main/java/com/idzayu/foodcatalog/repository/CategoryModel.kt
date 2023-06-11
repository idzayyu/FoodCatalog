package com.idzayu.foodcatalog.repository

import com.google.gson.annotations.SerializedName

data class CategoryModel(
    @SerializedName("сategories") val categories: ArrayList<Category>
)

data class Category(
    val id: Int,
    val name: String,
    @SerializedName("image_url") val imageUrl: String

)
