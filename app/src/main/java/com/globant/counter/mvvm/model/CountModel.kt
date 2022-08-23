package com.globant.counter.mvvm.model

import com.globant.counter.mvvm.contract.CountContract

class CountModel : CountContract.Model {

    override var count: Int = 0
        private set

    override fun reset() {
        count = 0
    }

    override fun inc() {
        count += 1
    }

    override fun dec() {
        count -= 1
    }
}
