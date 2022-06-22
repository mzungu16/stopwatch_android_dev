package com.example.stopwatch_android_dev.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch_android_dev.R
import com.example.stopwatch_android_dev.app
import com.example.stopwatch_android_dev.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var clicker = 0
        val viewModel = ViewModelProvider(
            this,
            MainActivityViewModel(app.usecase)
        ).get(MainActivityViewModel::class.java)
        binding.startBtn.setOnClickListener {
            println("TAG $clicker")
            when (clicker) {
                0 -> {
                    with(viewModel) {
                        startFirstCount()
                        firstInc.observe(this@MainActivity) {
                            binding.firstStopwatch.text = it.toString()
                        }
                    }
                    binding.startBtn.text = resources.getString(R.string.start_second)
                    clicker++
                }
                1 -> {
                    with(viewModel) {
                        startSecondCount()
                        secondInc.observe(this@MainActivity) {
                            binding.secondStopwatch.text = it.toString()
                        }
                    }
                }
            }
        }

        binding.stopBtn.setOnClickListener {
            viewModel.stopAll()
            clicker = 0
            binding.startBtn.text = resources.getString(R.string.start_first)
        }

    }
}