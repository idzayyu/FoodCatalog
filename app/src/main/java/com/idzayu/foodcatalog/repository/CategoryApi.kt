package com.idzayu.foodcatalog.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoryApi {
    @GET("{name}")
    fun getCommitsByName(
        @Path("name") name: String
    ): Call<CategoryModel>

    @GET("{name}")
    fun getDishList(
        @Path("name") name: String
    ): Call<DishModel>
}