package com.example.stopwatch_android_dev.data

import com.example.stopwatch_android_dev.domain.Usecase

class UsecaseImpl(private val api : MainActivityRepoImpl ) : Usecase {
    override val data: MainActivityRepoImpl
        get() = api
}