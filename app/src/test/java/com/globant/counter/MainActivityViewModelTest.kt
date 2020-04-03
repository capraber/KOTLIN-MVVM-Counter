package com.globant.counter

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.globant.counter.mvvm.viewmodel.MainActivityViewModel
import com.globant.counter.mvvm.viewmodel.states.CounterState
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainActivityViewModelTest {

    @get:Rule var rule: TestRule = InstantTaskExecutorRule()

    private val viewModel: MainActivityViewModel = MainActivityViewModel()

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
