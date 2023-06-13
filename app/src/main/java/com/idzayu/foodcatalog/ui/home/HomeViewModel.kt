package com.idzayu.foodcatalog.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    //var collection: LiveData<ArrayList<Category>>

    private val _text = MutableLiveData<String>().apply {
        value = "Село"
    }
    fun setLocality(locality: String){
        _text.value = locality
    }
    val text: LiveData<String> = _text



}