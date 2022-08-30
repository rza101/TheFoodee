package com.rhezarijaya.thefoodee.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.asLiveData
import com.rhezarijaya.core.data.FoodRepository
import com.rhezarijaya.core.data.Resource
import com.rhezarijaya.core.utils.Constants
import com.rhezarijaya.thefoodee.R
import com.rhezarijaya.thefoodee.databinding.ActivitySplashBinding
import com.rhezarijaya.thefoodee.ui.home.HomeActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activitySplashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(activitySplashBinding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, Constants.SPLASH_SCREEN_DELAY)
    }
}