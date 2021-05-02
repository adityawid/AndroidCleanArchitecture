package com.adityawidayanto.core

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication
import com.adityawidayanto.core.di.CoreComponent
import com.adityawidayanto.core.di.DaggerCoreComponent

class CoreApp : MultiDexApplication() {
    lateinit var coreComponent: CoreComponent

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
        fun coreComponent(context: Context) =
            (context.applicationContext as? CoreApp)?.coreComponent
    }

    fun getContext(): Context = applicationContext

    fun init(context: Context) {
        mContext = context
    }

    override fun onCreate() {
        super.onCreate()
        initCoreDependencyInjection()
    }

    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .application(this)
            .build()
    }
}