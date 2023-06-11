package com.idzayu.foodcatalog.repository


import android.app.Application
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppFoodCatalog: Application() {

    lateinit var apiDish: DishApi
    lateinit var api: CategoryApi

    override fun onCreate() {
        super.onCreate()
        instance = this

        val client = OkHttpClient.Builder()

        client.addInterceptor{ chain ->
            val original = chain.request()

            val request = original.newBuilder()
                .build()
            chain.proceed(request)
        }

        val retrofitDish = Retrofit.Builder()
            .baseUrl(BASE_DISH_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        val retrofitCategory = Retrofit.Builder()
            .baseUrl(BASE_CATEGORY_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()

        apiDish = retrofitDish.create(DishApi::class.java)
        api = retrofitCategory.create(CategoryApi::class.java)
    }

    companion object{
        const val BASE_DISH_URL = "https://run.mocky.io/v3/"
        const val BASE_CATEGORY_URL = "https://run.mocky.io/v3/"
        lateinit var instance: AppFoodCatalog
            private set
    }
}