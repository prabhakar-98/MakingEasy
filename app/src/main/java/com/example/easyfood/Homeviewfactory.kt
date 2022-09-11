package com.example.easyfood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easyfood.Roomdatabase.MealDatabase

class Homeviewfactory(val database:MealDatabase):ViewModelProvider.Factory{
    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(database) as T
    }
}