package com.idzayu.foodcatalog.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCategory {

    fun getLst(callback: MyCallback, name : String) {
        getCategoryList(callback, name)
    }

    fun getLst(callback: MyCallbackDish, name : String) {
        getDishList(callback, name)
    }

    private fun getCategoryList(callback: MyCallback, name : String){
        AppFoodCatalog.instance.api.getCommitsByName(name)
            .enqueue(object : Callback<CategoryModel> {
                override fun onResponse(
                    call: Call<CategoryModel>,
                    response: Response<CategoryModel>
                ) {

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

    private fun getDishList(callback: MyCallbackDish, name : String){
        AppFoodCatalog.instance.api.getDishList(name)
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
        fun onSuccess(result: CategoryModel)
        fun onFailure(error: Throwable)
    }
    interface MyCallbackDish {
        fun onSuccess(result: DishModel)
        fun onFailure(error: Throwable)
    }
}