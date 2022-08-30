package com.rhezarijaya.thefoodee.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.rhezarijaya.thefoodee.R
import com.rhezarijaya.thefoodee.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupWithNavController(
            binding.homeBottomNavView,
            (supportFragmentManager.findFragmentById(R.id.home_nav_host) as NavHostFragment).navController
        )
    }
}