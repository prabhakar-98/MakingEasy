package com.example.easyfood.Roomdatabase

import androidx.room.TypeConverter
import androidx.room.TypeConverters

@TypeConverters
class Converter {
    @TypeConverter
    fun convertanytostring(attribute:Any?):String?{
        return attribute.toString()
    }
     @TypeConverter
     fun convetstringtoany(attribut:String?):Any?{
         return attribut!!
     }
}