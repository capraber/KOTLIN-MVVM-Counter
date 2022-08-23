package com.globant.counter.mvvm.contract

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.globant.counter.mvvm.viewmodel.CountViewModel

interface CountContract {

    interface Model {
        val count: Int

        fun reset()
        fun inc()
        fun dec()
    }

    interface ViewModel {
        fun getValue(): LiveData<CountViewModel.CounterData>
        fun resetValue()
        fun incValue()
        fun decValue()
    }
}
