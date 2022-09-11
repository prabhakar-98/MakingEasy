package com.example.easyfood


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.pojo.MealX


class PopularAdapter():RecyclerView.Adapter<PopularAdapter.viewholder>(){
    private  lateinit var tyr:Context
    private var listitem= ArrayList<MealX>()
            @SuppressLint("NotifyDataSetChanged")
            fun getlistitem(mealist:List<MealX>){
                this.listitem= mealist as ArrayList<MealX>
                notifyDataSetChanged()
            }

    class viewholder(itemView:View):RecyclerView.ViewHolder(itemView) {
      var image =itemView.findViewById<ImageView>(R.id.image_c)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.popular_items,parent,false)
        tyr=parent.context
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        val currentmeal=listitem[position]
        Glide.with(holder.itemView).load(currentmeal.strMealThumb).into(holder.image)
        holder.image.setOnClickListener {
            val intent= Intent(tyr,MealActivity::class.java)
            intent.putExtra("meal_id",currentmeal.idMeal)
            intent.putExtra("MEAL_NAME",currentmeal.strMeal)
            intent.putExtra("meal_image",currentmeal.strMealThumb)
            tyr.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
      return listitem.size
    }
}