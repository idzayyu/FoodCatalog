package com.idzayu.foodcatalog.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "К сожелению аккаунт еще не готов"
    }
    val text: LiveData<String> = _text
}