package com.example.stopwatch_android_dev

import android.app.Application
import android.content.Context
import com.example.stopwatch_android_dev.data.MainActivityRepoImpl
import com.example.stopwatch_android_dev.data.UsecaseImpl
import com.example.stopwatch_android_dev.domain.Usecase

class App : Application() {
    private val repo by lazy { MainActivityRepoImpl() }
    val usecase: Usecase by lazy { UsecaseImpl(repo) }
}

val Context.app: App
    get() {
        return applicationContext as App
    }