package com.example.foodie.data

import androidx.lifecycle.LiveData
import com.example.foodie.model.Food

class FoodDatabaseImpl(    private val dao:FoodDao
) :FoodRepository{
    override fun getAllFoods(): LiveData<List<Food>> {
        return dao.getAllFoods()
    }

    override suspend fun insertFood(food: Food) {
        return dao.insertFood(food)
    }

    override suspend fun deleteFood(food: Food) {
        return dao.deleteFood(food)
    }

}