package com.example.easyfood.Roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.easyfood.pojo.Meal

@Dao
interface Daomeal {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getInsert(item:Meal)

    @Update
    suspend fun  getUpdate(item: Meal)

    @Delete
    suspend fun  getDelete(item:Meal)

    @Query("SELECT * FROM USER")
      fun getAll():LiveData<List<Meal>>

}