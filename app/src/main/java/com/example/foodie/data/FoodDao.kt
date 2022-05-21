package com.example.foodie.data


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.foodie.model.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: Food)

    @Delete
    suspend fun deleteFood(food:Food)

    @Query("SELECT * FROM foodDB order by image")
    fun getAllFoods(): LiveData<List<Food>>
}