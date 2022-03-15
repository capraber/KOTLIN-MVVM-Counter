package com.globant.counter.mvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globant.counter.mvvm.model.CountModel

class MainActivityViewModel : ViewModel() {

    private val model = CountModel()

    private val mutableLiveData: MutableLiveData<CounterData> = MutableLiveData()
    fun getValue(): MutableLiveData<CounterData> {
        return mutableLiveData
    }

    fun resetValue() {
        model.reset()
        mutableLiveData.value = CounterData(CounterState.INITIAL)
    }

    fun incValue() {
        model.inc()
        mutableLiveData.value = CounterData(CounterState.INC, model.count)
    }

    fun decValue() {
        model.dec()
        mutableLiveData.value = CounterData(CounterState.DEC, model.count)
    }

    data class CounterData(
        val state: CounterState = CounterState.INITIAL,
        val value: Int = 0
    )

    enum class CounterState {
        INITIAL,
        INC,
        DEC
    }
}
