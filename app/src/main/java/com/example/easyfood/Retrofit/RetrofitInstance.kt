package com.example.easyfood.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



object RetrofitInstance {

        val api:Mealapi by lazy { Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Mealapi::class.java)
        }



}