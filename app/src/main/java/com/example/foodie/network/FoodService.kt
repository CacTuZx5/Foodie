package com.example.foodie.network


import com.example.foodie.model.Food
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface FoodService {

    @GET("DisneyPosters2.json")
    suspend fun fetchDisneyPosterList(): ApiResponse<List<Food>>
}