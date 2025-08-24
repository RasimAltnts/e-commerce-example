package com.example.e_commerce

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.updatePadding
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.e_commerce.databinding.ActivityMainBinding
import com.google.android.material.badge.BadgeDrawable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var appBar: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var badge: BadgeDrawable


    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment

        //val navController = findNavController(R.id.nav_host_fragment_content_main)
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)
        appBar = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController , appBar)

        badge = binding.bottomNavigation.getOrCreateBadge(R.id.fragment_basket)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            binding.bottomNavigation.updatePadding(bottom = insets.systemWindowInsetBottom)
            binding.navHostFragmentContentMain.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }

        initFlows()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBar)
                || super.onSupportNavigateUp()
    }

    private fun initFlows() {
        lifecycleScope.launch {
            sharedViewModel.productList.collect { list ->
                if (list.isEmpty()) {
                    badge.isVisible = false
                } else {
                    badge.isVisible = true
                    badge.number = list.size
                }
            }
        }
    }
}