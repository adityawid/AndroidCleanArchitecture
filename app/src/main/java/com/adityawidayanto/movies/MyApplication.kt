package com.adityawidayanto.movies

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.adityawidayanto.core.CoreApp

class MyApplication : MultiDexApplication() {

    fun getContext(): Context = applicationContext
    override fun onCreate() {
        super.onCreate()
        CoreApp().init(this)
        MultiDex.install(this)
    }
}