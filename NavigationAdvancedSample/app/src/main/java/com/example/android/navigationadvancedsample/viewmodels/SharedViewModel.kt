package com.example.android.navigationadvancedsample.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _counter = MutableLiveData<Int>()
    val counter: LiveData<Int> get() { return _counter }

    fun increaseCounter() {
        val count = _counter.value ?: 0
        _counter.value = count + 1
    }

    fun decreaseCounter() {
        val count = _counter.value ?: 0
        _counter.value = count - 1
    }
}