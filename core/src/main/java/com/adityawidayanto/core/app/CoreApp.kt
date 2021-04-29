package com.adityawidayanto.core.app

import android.annotation.SuppressLint
import android.content.Context
import androidx.multidex.MultiDexApplication

class CoreApp : MultiDexApplication() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var mContext: Context
    }

    fun getContext(): Context = applicationContext

    fun init(context: Context) {
        mContext = context
    }
}