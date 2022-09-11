package com.example.easyfood

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.Retrofit.RetrofitInstance
import com.example.easyfood.Roomdatabase.MealDatabase
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealList
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModal(private val db:MealDatabase):ViewModel(){

    private  var Mealdetails=MutableLiveData<Meal>()


    fun getdetais(id:String){
        RetrofitInstance.api.getmealdetails(id).enqueue(object :Callback<MealList>{
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
               Mealdetails.value=response.body()!!.meals[0]
            }

            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.d("meal",t.message.toString())
            }

        })
    }
    fun obserbermealdetails():LiveData<Meal>{
        return  Mealdetails

    }
    fun insert(meal: Meal){
     viewModelScope.launch {
       db.mealdao().getInsert(meal)
     }
    }


}