package com.example.easyfood.Retrofit


import com.example.easyfood.pojo.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Mealapi {
    @GET("random.php")
    fun   getapi ():Call<MealList>

    @GET("categories.php")
    fun getcategory():Call<Category>

    @GET("filter.php?")
    fun getonepopular(@Query("c") name :String):Call<PopularList>

    @GET("lookup.php?")
    fun getmealdetails(@Query("i") detail:String):Call<MealList>

}