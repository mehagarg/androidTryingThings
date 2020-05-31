package com.example.stateflowexamples

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StateFlowEgViewModel : ViewModel(){
    private val counterValue: MutableLiveData<Int> = MutableLiveData(1)

    fun getCounterValue() : LiveData<Int> = counterValue
}

