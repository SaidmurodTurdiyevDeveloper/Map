package com.example.mytracleapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.mytracleapp.R
import com.example.mytracleapp.databinding.ActivityMainBinding
import com.example.mytracleapp.other.Constants.ACTION_SHOW_TRACKING_FRAGMENT
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint
import java.util.HashSet

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding get() = _binding!!
    private var navController: NavController? = null
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigateToTrackingFragmentIfNeeded(intent)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.runFragment,
                R.id.statisticsFragment,
                R.id.settingsFragment
            )
        )
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.flFragment) as NavHostFragment
        navController = navHostFragment.findNavController()
        navController?.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.runFragment -> {
                    changeBottomnav(true)
                }
                R.id.statisticsFragment -> {
                    changeBottomnav(true)
                }
                R.id.settingsFragment -> {
                    changeBottomnav(true)
                }
                else -> changeBottomnav(false)
            }
        }
        navController?.also {
            setupWithNavController(binding.bottomNavigationView, it)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateToTrackingFragmentIfNeeded(intent)
    }

    private fun navigateToTrackingFragmentIfNeeded(intent: Intent?) {
        if (intent?.action == ACTION_SHOW_TRACKING_FRAGMENT) {
            if (navController == null) {
                (supportFragmentManager.findFragmentById(R.id.flFragment) as NavHostFragment).findNavController().navigate(R.id.action_global_trackingFragment)
            } else {
                navController?.navigate(R.id.action_global_trackingFragment)
                var d=LatLng(67.09,9.7)
            }
        }


    }

    private fun changeBottomnav(cond: Boolean) {
        binding.bottomNavigationView.isVisible = cond
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController?.navigateUp() ?: false || super.onSupportNavigateUp()
    }

    private fun showBottomBar() {
        binding.bottomNavigationView.visibility = View.VISIBLE
    }

    private fun hideBottomBar() {
        binding.bottomNavigationView.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}