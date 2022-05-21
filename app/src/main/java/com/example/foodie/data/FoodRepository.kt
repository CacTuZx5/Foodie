package com.example.foodie.data

import androidx.lifecycle.LiveData
import com.example.foodie.model.Food

interface FoodRepository {
    fun getAllFoods(): LiveData<List<Food>>
    suspend fun insertFood(food: Food)
    suspend fun deleteFood(food:Food)


}