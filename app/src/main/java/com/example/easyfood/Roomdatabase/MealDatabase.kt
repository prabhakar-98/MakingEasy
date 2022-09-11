package com.example.easyfood.Roomdatabase

import android.content.Context
import androidx.room.*
import com.example.easyfood.pojo.Meal

@Database(entities = [Meal::class], version = 1)
@TypeConverters(Converter::class)
 abstract class MealDatabase:RoomDatabase() {
     abstract fun mealdao():Daomeal


     companion object {
         @Volatile
         private var INSTANCE: MealDatabase? = null

         @Synchronized
         fun getinstance(context: Context): MealDatabase {
             if ( INSTANCE== null) {
                 INSTANCE=Room.databaseBuilder(context.applicationContext,MealDatabase::class.java,"username").build()
             }
          return INSTANCE as MealDatabase
         }

     }
}
