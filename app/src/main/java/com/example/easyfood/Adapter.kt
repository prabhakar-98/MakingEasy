package com.example.easyfood

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.easyfood.pojo.MealX

class Adapter:RecyclerView.Adapter<Adapter.viewholder>() {
    private  lateinit var hdf:Context
    private var arraylist= ArrayList<MealX>()
    @SuppressLint("NotifyDataSetChanged")
    fun setarrylist(list:List<MealX>){
        arraylist=list as ArrayList<MealX>
        notifyDataSetChanged()
    }
    class viewholder(itemView:View):RecyclerView.ViewHolder(itemView){
        val  image =itemView.findViewById<ImageView>(R.id.igeView58)
        val   thg =itemView.findViewById<TextView>(R.id.fav_name58)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.finalcategoryitem,parent,false)
         hdf=parent.context
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
       val current= arraylist[position]
        GlideApp.with(holder.itemView).load(current.strMealThumb).into(holder.image)
        holder.thg.text=current.strMeal
        holder.image.setOnClickListener {
            val intent=Intent(hdf,MealActivity::class.java)
            intent.putExtra("meal_id",current.idMeal)
            intent.putExtra("MEAL_NAME",current.strMeal)
            intent.putExtra("meal_image",current.strMealThumb)
            hdf.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
      return arraylist.size
    }
}