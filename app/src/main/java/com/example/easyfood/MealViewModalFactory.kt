package com.example.easyfood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.easyfood.Roomdatabase.MealDatabase


class MealViewModalFactory( private  val data:MealDatabase):ViewModelProvider.Factory {

    override  fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MealViewModal(data) as T
    }
}