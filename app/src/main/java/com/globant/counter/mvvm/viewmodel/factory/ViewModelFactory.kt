package com.globant.counter.mvvm.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.globant.counter.mvvm.contract.CountContract
import com.globant.counter.mvvm.viewmodel.CountViewModel

// If any dependency injector will be used, this class is not necessary
class ViewModelFactory(private val params: Array<Any>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CountViewModel::class.java -> CountViewModel(params[0] as CountContract.Model) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
