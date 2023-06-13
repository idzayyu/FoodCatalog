package com.idzayu.foodcatalog.repository

import android.util.ArrayMap

object Repo {
    fun getPurchaseAmount(): String {
        var purchaseAmount = 0
        for (i in dishesBasket.values){
            purchaseAmount += i.price.toInt() * i.cntBasket
        }
        return "Оплатить $purchaseAmount"
    }

    val categories = ArrayList<Category>()
    val dishes = ArrayList<Dish>()
    val dishesBasket = ArrayMap<Int,Dish>()
    val params = ArrayMap<Int,String>()
    val filterDish = ArrayMap<String, Boolean>()
}