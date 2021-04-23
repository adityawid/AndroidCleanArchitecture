package com.adityawidayanto.androidcleanarchitecture.view.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.adityawidayanto.androidcleanarchitecture.R
import com.adityawidayanto.androidcleanarchitecture.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var splashBinding :ActivitySplashBinding? = null
    private val splashTimeout = 5500L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding?.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, splashTimeout)
    }
}