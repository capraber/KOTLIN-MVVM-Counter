package com.globant.counter.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.globant.counter.R
import com.globant.counter.databinding.ActivityMainBinding
import com.globant.counter.mvvm.model.CountModel
import com.globant.counter.mvvm.viewmodel.CountViewModel
import com.globant.counter.mvvm.viewmodel.CountViewModel.CounterData
import com.globant.counter.mvvm.viewmodel.CountViewModel.CounterState
import com.globant.counter.mvvm.viewmodel.factory.ViewModelFactory

class CountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = ViewModelProvider(this, ViewModelFactory(arrayOf(CountModel()))).get(CountViewModel::class.java)
        binding.lifecycleOwner = this

        binding.viewModel?.getValue()?.observe(this) { updateUI(it) }
    }

    private fun updateUI(it: CounterData) {
        when (it.state) {
            CounterState.INITIAL -> {
                binding.countLabel.text = getString(R.string.txt_starting_count_label_value)
                showToast(getString(R.string.reset_text))
            }
            CounterState.INC -> {
                binding.countLabel.text = it.value.toString()
                showToast(getString(R.string.incremented_text))
            }
            CounterState.DEC -> {
                binding.countLabel.text = it.value.toString()
                showToast(getString(R.string.decremented_text))
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
