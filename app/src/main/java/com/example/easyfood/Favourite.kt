package com.example.easyfood

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.Retrofit.RetrofitInstance
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealX
import com.example.easyfood.pojo.PopularList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class Favourite : Fragment() {
   private  lateinit var recyclerView: RecyclerView
   private lateinit var favadapter:FavouriteAdapter
   private lateinit var viewModel:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val view=inflater.inflate(R.layout.fragment_favourite, container, false)
         recyclerView=view.findViewById(R.id.fav_cycle)
         favadapter=FavouriteAdapter()
         viewModel=(activity as MainActivity).viewModel
         observerfavmeal()
        recyclerView.apply {
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)
            adapter=favadapter
        }


        return  view
    }

    private fun observerfavmeal() {
        viewModel.observerfavdata().observe(viewLifecycleOwner, androidx.lifecycle.Observer {
        favadapter.differ.submitList(it)
        })
    }


}