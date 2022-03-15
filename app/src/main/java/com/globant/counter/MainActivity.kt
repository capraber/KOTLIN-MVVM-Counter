package com.globant.counter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.globant.counter.databinding.ActivityMainBinding
import com.globant.counter.mvvm.viewmodel.MainActivityViewModel
import com.globant.counter.mvvm.viewmodel.MainActivityViewModel.CounterData
import com.globant.counter.mvvm.viewmodel.MainActivityViewModel.CounterState

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.lifecycleOwner = this

        binding.viewModel?.getValue()?.observe(this, Observer { updateUI(it) })
    }

    private fun updateUI(it: CounterData) {
        when (it.state) {
            CounterState.INITIAL -> {
                binding.countLabel.text = getString(R.string.txt_main_activity_starting_count_label_value)
                showToast(getString(R.string.main_activity_toast_reset_text))
            }
            CounterState.INC -> {
                binding.countLabel.text = it.value.toString()
                showToast(getString(R.string.main_activity_toast_incremented_text))
            }
            CounterState.DEC -> {
                binding.countLabel.text = it.value.toString()
                showToast(getString(R.string.main_activity_toast_decremented_text))
            }
        }
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
