package com.example.stopwatch_android_dev.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class MainActivityRepoImpl {
    private var firstInc: Int = 0
    private var secondInc: Int = 0

    val firstFlow = flow {
        while (true) {
            delay(1000)
            emit(firstInc++)
        }

    }

    val secondFlow = flow {
        while (true) {
            delay(1000)
            emit(secondInc++)
        }
    }
}