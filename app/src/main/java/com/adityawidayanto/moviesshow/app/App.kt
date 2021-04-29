package com.adityawidayanto.moviesshow.app

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.adityawidayanto.core.app.CoreApp
import com.adityawidayanto.moviesshow.di.AppComponent
import com.adityawidayanto.moviesshow.di.DaggerAppComponent

class App : MultiDexApplication() {
    private lateinit var appComponent: AppComponent

    fun getContext(): Context = applicationContext
    override fun onCreate() {
        super.onCreate()
        CoreApp().init(this)
        appComponent = DaggerAppComponent.builder()
            .build()
        MultiDex.install(this)
    }
}