package com.example.easyfood

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easyfood.pojo.CategoryX

class CategoryAdapter():RecyclerView.Adapter<CategoryAdapter.viewholder>() {
    private var list= ArrayList<CategoryX>()
    private  lateinit var context: Context
    @SuppressLint("NotifyDataSetChanged")
    fun getclist(list: List<CategoryX>){
        this.list= list as ArrayList<CategoryX>
        notifyDataSetChanged()
    }

    class viewholder(itemView:View):RecyclerView.ViewHolder(itemView){
        val imge=itemView.findViewById<ImageView>(R.id.cat_item)!!
        val taxt=itemView.findViewById<TextView>(R.id.textView4)!!

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
      val view=LayoutInflater.from(parent.context).inflate(R.layout.category_items,parent,false)
        context=parent.context
        return viewholder(view)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
     val cureent=list[position]
        Glide.with(holder.itemView).load(cureent.strCategoryThumb).into(holder.imge)
        holder.taxt.text=cureent.strCategory
        holder.imge.setOnClickListener {
            val intent=Intent(context,CategoryList::class.java)
            intent.putExtra("Name",cureent.strCategory)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return list.size
    }
}