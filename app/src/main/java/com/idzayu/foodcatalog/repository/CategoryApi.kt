package com.idzayu.foodcatalog.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CategoryApi {
    @GET("058729bd-1402-4578-88de-265481fd7d54")
    fun getCommitsByName(): Call<CategoryModel>
}