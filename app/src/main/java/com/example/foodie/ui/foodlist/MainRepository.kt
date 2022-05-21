package com.example.foodie.ui.foodlist

import com.example.foodie.data.FoodDao
import com.example.foodie.network.FoodService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val foodService: FoodService,
    private val foodDao: FoodDao)
{
    //val foodlist: LiveData<List<Food>> = foodDao.getAllFoods()

}