package com.example.easyfood

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.pojo.Category
import com.example.easyfood.pojo.CategoryX
import java.util.*


class Category : Fragment() {
   private lateinit var recyler:RecyclerView
   private lateinit var adapterc:CategoryAdapter

   private  lateinit var modelview:HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_category, container, false)
        modelview=(activity as MainActivity).viewModel
        adapterc=CategoryAdapter()
        modelview.getcategorymeal()
        putlistemitem()
        recyler= view.findViewById(R.id.rec2)
        recyler.adapter=adapterc
        recyler.apply {
            layoutManager=GridLayoutManager(context,2,GridLayoutManager.VERTICAL,false)

        }


        return  view
    }

    private fun putlistemitem() {
        modelview.obserbercategory().observe(viewLifecycleOwner,object : Observer<Category> {

            override fun onChanged(t: Category?) {
                adapterc.getclist(t!!.categories)

            }

        })


    }


}