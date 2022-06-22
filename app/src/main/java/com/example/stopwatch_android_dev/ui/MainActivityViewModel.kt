package com.example.stopwatch_android_dev.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stopwatch_android_dev.domain.Usecase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect

class MainActivityViewModel(val usecase: Usecase) : ViewModel(), ViewModelProvider.Factory {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val jobs = mutableListOf<Job>()
    private val _firstInc = MutableLiveData<Int>()
    private val _secondInc = MutableLiveData<Int>()
    val firstInc: LiveData<Int>
        get() = _firstInc
    val secondInc: LiveData<Int>
        get() = _secondInc

    fun startFirstCount() {
        jobs.add(scope.launch {
            usecase.data.firstFlow.collect {
                println("TAG1 $it")
                _firstInc.postValue(it)
            }
        })
    }

    fun startSecondCount() {
        jobs.add(scope.launch {
            usecase.data.secondFlow.collect {
                println("TAG2 $it")
                _secondInc.postValue(it)
            }
        })
    }

    fun stopAll() {
        jobs.forEach { job -> job.cancel() }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(usecase) as T
    }
}