package com.example.foodie.network

import com.example.foodie.model.Food
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodService {
    @GET("/api")
    suspend fun getFoods(): Response<Food>

    @POST("/images/")
    fun createNewFood(): Call<Food>

    @DELETE("/images/")
    fun deleteFood(): Call<Food>
}