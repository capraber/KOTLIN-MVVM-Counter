package com.globant.counter.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.globant.counter.mvvm.contract.CountContract
import com.globant.counter.mvvm.model.CountModel

class CountViewModel(private val model: CountContract.Model) : ViewModel(), CountContract.ViewModel {

    private val mutableLiveData: MutableLiveData<CounterData> = MutableLiveData()
    override fun getValue(): LiveData<CounterData> {
        return mutableLiveData
    }

    override fun resetValue() {
        model.reset()
        mutableLiveData.value = CounterData(CounterState.INITIAL)
    }

    override fun incValue() {
        model.inc()
        mutableLiveData.value = CounterData(CounterState.INC, model.count)
    }

    override fun decValue() {
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
