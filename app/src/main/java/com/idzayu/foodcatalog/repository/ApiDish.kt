package com.idzayu.foodcatalog.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiDish {

    fun getCategory(callback: MyCallback) {
        getCategoryList(callback)
    }

    private fun getCategoryList(callback: MyCallback){
        AppFoodCatalog.instance.apiDish.getCommitsByName()
            .enqueue(object : Callback<DishModel> {
                override fun onResponse(
                    call: Call<DishModel>,
                    response: Response<DishModel>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            callback.onSuccess(it)
                        }?.let { }
                    }
                }
                override fun onFailure(call: Call<DishModel>, t: Throwable) {
                    callback.onFailure(t)
                    return
                }
            })
    }
    interface MyCallback {
        fun onSuccess(result: DishModel)
        fun onFailure(error: Throwable)
    }
}