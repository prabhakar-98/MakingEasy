package com.example.easyfood

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easyfood.Retrofit.RetrofitInstance
import com.example.easyfood.Roomdatabase.MealDatabase
import com.example.easyfood.pojo.*
import com.example.easyfood.pojo.Category
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class HomeViewModel(private val db:MealDatabase) : ViewModel() {
       private    var randommeal=MutableLiveData< Meal>()
        private   var popularmeal=MutableLiveData<PopularList>()
        private    var categorymeal=MutableLiveData<Category>()
            fun getreandommeal(){
                RetrofitInstance.api.getapi().enqueue(object : Callback<MealList> {
                    override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                        if(response.body()!=null){

                            val meal: Meal =response.body()!!.meals[0]
                            randommeal.value=meal
                        }


                    }

                    override fun onFailure(call: Call<MealList>, t: Throwable) {
                        Log.d("HOME",t.message.toString())
                    }


                })
            }
       fun oberservelivedata():LiveData<Meal>{
        return  randommeal
    }
    fun getpopularmeal(){
       RetrofitInstance.api.getonepopular("Seafood").enqueue(object :Callback<PopularList>{
           override fun onResponse(call: Call<PopularList>, response: Response<PopularList>) {
               if(response.body()!=null) {
                   popularmeal.value = response.body()!!
               }
               else {
                   return
               }

           }

           override fun onFailure(call: Call<PopularList>, t: Throwable) {
               Log.d("home",t.message.toString())
           }

       })
    }
    fun obserberpopular():LiveData<PopularList>{
        return popularmeal
    }

    fun getcategorymeal(){
     RetrofitInstance.api.getcategory().enqueue(object :Callback<Category>{
         override fun onResponse(call: Call<Category>, response: Response<Category>) {
             if (response.body()!=null) {
                 categorymeal.value = response.body()!!
             }
             else
             {
                 return
             }
         }

         override fun onFailure(call: Call<Category>, t: Throwable) {
           Log.d("hoME",t.message.toString())
         }

     })
    }
    fun obserbercategory():LiveData<Category>{
        return  categorymeal
    }
    fun observerfavdata():LiveData<List<Meal>>{
        return db.mealdao().getAll()
    }
    fun delete(meal:Meal){
        viewModelScope.launch {
            db.mealdao().getDelete(meal)
        }
    }
}