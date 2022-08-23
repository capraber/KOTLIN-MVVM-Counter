package com.globant.counter.unitTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.counter.mvvm.contract.CountContract
import com.globant.counter.mvvm.model.CountModel
import com.globant.counter.mvvm.viewmodel.CountViewModel
import com.globant.counter.mvvm.viewmodel.CountViewModel.CounterState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class CountViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CountContract.ViewModel

    @Before
    fun setup() {
        viewModel = CountViewModel(CountModel())
    }

    @Test
    fun initialStateTest() {
        assert(viewModel.getValue().value == null)
    }

    @Test
    fun onPressResetAfterInitialStateTest() {
        viewModel.resetValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CounterState.INITIAL)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun onPressResetAfterPressIncTest() {
        viewModel.incValue()
        viewModel.resetValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CounterState.INITIAL)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun onPressResetAfterPressDecTest() {
        viewModel.decValue()
        viewModel.resetValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CounterState.INITIAL)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun onPressIncAfterInitialStateTest() {
        viewModel.incValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CounterState.INC)
        assert(viewModel.getValue().value?.value == ONE)
    }

    @Test
    fun onPressDecAfterInitialStateTest() {
        viewModel.decValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CounterState.DEC)
        assert(viewModel.getValue().value?.value == MINUS_ONE)
    }

    @Test
    fun onPressDecAfterPressIncTest() {
        viewModel.incValue()
        viewModel.decValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CounterState.DEC)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    @Test
    fun onPressIncAfterPressDecTest() {
        viewModel.decValue()
        viewModel.incValue()

        assert(viewModel.getValue().value != null)
        assert(viewModel.getValue().value?.state == CounterState.INC)
        assert(viewModel.getValue().value?.value == ZERO)
    }

    companion object {
        private const val ZERO = 0
        private const val MINUS_ONE = -1
        private const val ONE = 1
    }
}
