package com.example.easyfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.easyfood.Retrofit.RetrofitInstance
import com.example.easyfood.databinding.ActivityCategoryListBinding
import com.example.easyfood.pojo.PopularList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryList : AppCompatActivity() {
    private  lateinit var adapter:Adapter
    private lateinit var binding: ActivityCategoryListBinding
    private   var name:String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCategoryListBinding.inflate(layoutInflater)
        setContentView(binding.root)

          val inte=intent
          name=inte.getStringExtra("Name")
          adapter=Adapter()
          settlist()
          setrecyle()
          binding.end.adapter=adapter
          binding.textView7.text=name
    }

    private fun setrecyle() {
        binding.end.apply {
            layoutManager=GridLayoutManager(this@CategoryList,2,GridLayoutManager.VERTICAL,false)
        }
    }

    private fun settlist() {
        RetrofitInstance.api.getonepopular(name!!).enqueue(object :Callback<PopularList>{
            override fun onResponse(call: Call<PopularList>, response: Response<PopularList>) {
                adapter.setarrylist(response.body()!!.meals)
            }

            override fun onFailure(call: Call<PopularList>, t: Throwable) {
                Log.d("final",t.message.toString())
            }

        })
    }

}