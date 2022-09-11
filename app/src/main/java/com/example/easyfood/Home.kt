package com.example.easyfood

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.easyfood.databinding.FragmentHomeBinding
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.CategoryX
import com.example.easyfood.pojo.Meal
import com.example.easyfood.pojo.MealX
import com.example.easyfood.pojo.PopularList


class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private  lateinit var homeviewmodel:HomeViewModel
     private lateinit var adapter: PopularAdapter
    private  lateinit var categoryAdapter: CategoryAdapter
    private lateinit var intentrandommeal:Meal



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeviewmodel = (activity as MainActivity).viewModel
        adapter = PopularAdapter()
        categoryAdapter=CategoryAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=FragmentHomeBinding.inflate(inflater,container,false)


        return binding.root

    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeviewmodel.getreandommeal()
        homeviewmodel.oberservelivedata().observe(viewLifecycleOwner
        ) { t -> Glide.with(this@Home).load(t!!.strMealThumb).into(binding.image1)
            intentrandommeal= t
        }
        homeviewmodel.getpopularmeal()
        setpopuler()
        homeviewmodel.getcategorymeal()
        setcatgory()
        binding.image1.setOnClickListener {
            val intent=Intent(context,MealActivity::class.java)
            intent.putExtra("meal_id",intentrandommeal.idMeal)
            intent.putExtra("MEAL_NAME",intentrandommeal.strMeal)
            intent.putExtra("meal_image",intentrandommeal.strMealThumb)

            startActivity(intent)


        }







     binding.recyclerView.apply {
         layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
         adapter=adapter
     }
        binding.recyclerView.adapter = adapter





        binding.cycle.apply {
            layoutManager=GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
            adapter=categoryAdapter
        }
        binding.cycle.adapter=categoryAdapter

    }


    private fun setcatgory() {
        homeviewmodel.obserbercategory().observe(viewLifecycleOwner,object :Observer<Category>{

            override fun onChanged(t: Category?) {
                categoryAdapter.getclist(t!!.categories)

            }

        })

    }


    private fun setpopuler() {
        homeviewmodel.obserberpopular().observe(viewLifecycleOwner,object :Observer<PopularList>{

            override fun onChanged(t: PopularList?) {
                adapter.getlistitem(t!!.meals)

            }

        })

    }
}