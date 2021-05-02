package com.adityawidayanto.core.network

import android.content.Context
import android.net.ConnectivityManager
import com.adityawidayanto.core.CoreApp
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NoConnectionInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isConnectionOn(CoreApp.mContext)) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }

    private fun isConnectionOn(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected
    }

    class NoConnectivityException : IOException() {
        override val message: String
            get() = "No Internet Connection"
    }
}