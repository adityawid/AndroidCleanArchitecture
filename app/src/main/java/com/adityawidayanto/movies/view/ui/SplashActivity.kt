package com.adityawidayanto.movies.view.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.adityawidayanto.movies.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private var splashBinding: ActivitySplashBinding? = null
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