package com.example.foodie.network

import com.example.foodie.model.Food
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodService {
    @GET("api")
    fun getFoods(): Call<Food>

    @GET("images/pizza/pizza72.jpg")
    fun getOneFood(): Call<Food>

    @POST("images/")
    fun createNewFood(f:Food): Call<Food>

    @DELETE("images/{id}")
    fun deleteFood(f:Food): Call<Food>
}