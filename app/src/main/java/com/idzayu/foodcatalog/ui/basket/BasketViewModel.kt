package com.idzayu.foodcatalog.ui.basket

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.idzayu.foodcatalog.repository.Repo

class BasketViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = Repo.getPurchaseAmount()
    }
    private val _locality = MutableLiveData<String>().apply {
        value = Repo.params[1]
    }
    
    val text: LiveData<String> = _text
    val locality: LiveData<String> = _locality
}