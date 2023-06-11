package com.idzayu.foodcatalog.repository

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCategory {

    fun getCategory(callback: MyCallback) {
        getCategoryList(callback)
    }

    private fun getCategoryList(callback: MyCallback){
        AppFoodCatalog.instance.api.getCommitsByName()
            .enqueue(object : Callback<CategoryModel> {
                override fun onResponse(
                    call: Call<CategoryModel>,
                    response: Response<CategoryModel>
                ) {

                    Log.d("CategoryList", "1Response: " + response.isSuccessful)
                    Log.d("CategoryList", "1Response: " + response.body())
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onSuccess(it)
                        }?.let { }
                    }
                }
                override fun onFailure(call: Call<CategoryModel>, t: Throwable) {
                    callback.onFailure(t)
                    return
                }
            })
    }
    interface MyCallback {
        fun onSuccess(result: CategoryModel)
        fun onFailure(error: Throwable)
    }
}