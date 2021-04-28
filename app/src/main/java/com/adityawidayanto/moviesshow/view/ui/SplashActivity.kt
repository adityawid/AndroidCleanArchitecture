package com.adityawidayanto.moviesshow.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.adityawidayanto.moviesshow.R
import com.adityawidayanto.moviesshow.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var splashBinding: ActivitySplashBinding? = null
    private val splashTimeout = 1000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding =  DataBindingUtil.setContentView(this, R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, splashTimeout)
    }
}