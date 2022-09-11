package com.example.easyfood


import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.bumptech.glide.Glide
import com.example.easyfood.Roomdatabase.MealDatabase
import com.example.easyfood.databinding.ActivityMealBinding
import com.example.easyfood.pojo.Meal

class MealActivity : AppCompatActivity() {
    private lateinit var mealBinding: ActivityMealBinding
    private  lateinit var mealid:String
    private  lateinit var mealname:String
    private  lateinit var mealimge:String
    private  lateinit var viewmealmodal:MealViewModal
    private lateinit var youtubelink:String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mealBinding=ActivityMealBinding.inflate(layoutInflater)
        setContentView(mealBinding.root)
        val database= MealDatabase.getinstance(this)
        val modalfactory=MealViewModalFactory(database)
        viewmealmodal= ViewModelProvider(this,modalfactory)[MealViewModal::class.java]
        getextrainfro()
        putimageintoactivity()
        viewmealmodal.getdetais(mealid)
        obwerberinformation()
        onclickyoutubeimage()
        onclickfavoritebutton()

    }

    private fun onclickfavoritebutton() {
       mealBinding.save.setOnClickListener {
           viewmealmodal.insert(favlist!!)
           Toast.makeText(this@MealActivity,"MEAL SAVED",Toast.LENGTH_SHORT).show()
       }
    }

    private fun onclickyoutubeimage() {
        mealBinding.youtub.setOnClickListener {
            val intent=Intent(Intent.ACTION_VIEW, Uri.parse(youtubelink))
            startActivity(intent)
        }
    }
    private  var favlist: Meal? =null
    private fun obwerberinformation() {
        viewmealmodal.obserbermealdetails().observe(this,object :Observer<Meal>{
            override fun onChanged(t: Meal?) {
                mealBinding.detail.text="Category:${t!!.strInstructions}"
                mealBinding.textView5.text="Area:${t.strCategory}"
                mealBinding.area.text= t.strArea
                youtubelink=t.strYoutube
                favlist=t
            }

        })
    }

    private fun putimageintoactivity() {
       Glide.with(this).load(mealimge).into(mealBinding.imrandonm)
    }

    private fun getextrainfro() {
       val intent1=intent
        mealid= intent1.getStringExtra("meal_id")!!
        mealname=intent1.getStringExtra("MEAL_NAME")!!
        mealimge=intent1.getStringExtra("meal_image")!!
    }
}