package com.example.foodie.network

import com.example.foodie.model.Food
import com.example.foodie.model.Foodmodel
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodService {
    @GET("api")
    fun getFoods(): Call<Foodmodel>

    @POST("/images/")
    fun createNewFood(): Call<Food>

    @DELETE("/images/")
    fun deleteFood(f:Foodmodel): Call<Foodmodel>
}