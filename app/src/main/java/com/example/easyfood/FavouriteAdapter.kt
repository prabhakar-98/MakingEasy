package com.example.easyfood

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.pojo.Meal

class FavouriteAdapter():RecyclerView.Adapter<FavouriteAdapter.viewholder>() {
    private  lateinit var dgh:Context
     private  val diffutil=object :DiffUtil.ItemCallback<Meal>(){
         override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
             return oldItem.idMeal==newItem.idMeal
         }

         override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
           return oldItem==newItem
         }
     }
    val differ=AsyncListDiffer(this@FavouriteAdapter,diffutil)

    class viewholder(itemview:View):RecyclerView.ViewHolder(itemview){
        val imagemeal=itemview.findViewById<ImageView>(R.id.igeView)
       val mealnametext=itemview.findViewById<TextView>(R.id.fav_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.favouriteitem,parent,false)
        dgh=parent.context
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       val current=differ.currentList[position]
        Glide.with(holder.itemView).load(current.strMealThumb).into(holder.imagemeal)
        holder.mealnametext.text=current.strMeal

    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

}