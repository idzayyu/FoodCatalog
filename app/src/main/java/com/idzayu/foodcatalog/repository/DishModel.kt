package com.idzayu.foodcatalog.repository

import com.google.gson.annotations.SerializedName


data class DishModel(
    val dishes: ArrayList<Dish>
)

data class Dish(
    val id: Int,
    val name: String,
    val price: String,
    val weight: String,
    val description: String,
    @SerializedName("image_url") val imageUrl: String,
    @SerializedName("tegs") val tags: List<String>
)
