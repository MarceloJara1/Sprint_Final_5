package com.example.sprintfinalmodulo5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.sprintfinalmodulo5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = binding.topAppBar
        setSupportActionBar(toolbar)

        val bottomNavigation = binding.bottomNavigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigation.setupWithNavController(navController)
        bottomNavigation.selectedItemId = R.id.itemProducts

        bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.itemProducts -> {
                    if (navController.currentDestination?.id == R.id.itemSingleProduct) {
                        navController.navigate(R.id.action_itemSingleProduct_to_itemProducts)
                        true
                    } else if (navController.currentDestination?.id == R.id.cart) {
                        navController.navigate(R.id.action_cart_to_itemProducts)
                        true
                    } else {
                        false
                    }
                }
                R.id.cart -> {
                    if (navController.currentDestination?.id == R.id.itemSingleProduct) {
                        navController.navigate(R.id.action_itemSingleProduct_to_cart)
                        true
                    } else if (navController.currentDestination?.id == R.id.itemProducts) {
                        navController.navigate(R.id.action_itemProducts_to_cart)
                        true
                    } else {
                        false
                    }
                }
                else -> false
            }
        }
        navController.addOnDestinationChangedListener{_, destination, _ ->
            supportActionBar?.title = when (destination.id){
                R.id.itemProducts -> "Our Products"
                R.id.itemSingleProduct -> ""
                R.id.cart -> "My Cart"
                else->""
            }
        }

    }


}