package com.example.easyfood

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.easyfood.Roomdatabase.MealDatabase
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
     private lateinit var navController:NavController
   val viewModel:HomeViewModel  by lazy {
       val datbase=MealDatabase.getinstance(this)
       val viewprovider=Homeviewfactory(datbase)
       ViewModelProvider(this,viewprovider)[HomeViewModel::class.java]
   }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val naveHostFragment=supportFragmentManager.findFragmentById(R.id.mainfrag) as NavHostFragment
        val bottomNavigation=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        navController=naveHostFragment.navController


        setupWithNavController(bottomNavigation,navController)

    }
}