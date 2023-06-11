package com.idzayu.foodcatalog.repository

import retrofit2.Call
import retrofit2.http.GET

interface DishApi {
    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    fun getCommitsByName(): Call<DishModel>
}