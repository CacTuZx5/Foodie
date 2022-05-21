package com.example.foodie.ui.foodlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.di.NetworkModule
import com.example.foodie.model.Food
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel():ViewModel() {
    private var foodLiveData= MutableLiveData<Food>()
    private var mock = true

    fun createNewFood(f:Food){
    viewModelScope.launch {
        //foodDatabase.foodDao().insertFood(f)
    }
    if (mock){
        foodLiveData.value=f
        Log.d("creating new item","${f.image}")
    }
    else {
        NetworkModule.api.createNewFood(f).enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    Log.d("delete","${response.body()}")
                }
                else{
                    return
                }

            }
            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food creating error",t.message.toString())
            }
        })
    }
}

    fun getOneFood(){
        NetworkModule.api.getOneFood().enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    val fooditem: Food = response.body()!!
                    foodLiveData.value=fooditem
                    val list = fooditem.image.split("/")
                    val name=list[4]
                    //Glide.with(this@FirstFragment).load(fooditem.image).into(binding.imgMeal)

                    Log.d("TEST","food name ${fooditem.image} ")
                    Log.d("food name","${name}")
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food list fragment error",t.message.toString())
            }
        })
    }

    fun getRandomFood(){
        NetworkModule.api.getFoods().enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    val fooditem: Food = response.body()!!
                    foodLiveData.value=fooditem
                    val list = fooditem.image.split("/")
                    val name=list[4]
                    //Glide.with(this@FirstFragment).load(fooditem.image).into(binding.imgMeal)

                    Log.d("TEST","food name ${fooditem.image} ")
                    Log.d("food name","${name}")
                }
                else{
                    return
                }
            }
            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food list fragment error",t.message.toString())
            }
        })
    }

    fun deleteFood(f:Food){
        viewModelScope.launch {
            //foodDatabase.foodDao().deleteFood(f)
        }
        if (mock){

        }
        else {
            NetworkModule.api.deleteFood(f).enqueue(object : Callback<Food> {
                override fun onResponse(call: Call<Food>, response: Response<Food>) {
                    if (response.body()!=null){
                        Log.d("delete","${response.body()}")
                    }
                    else{
                        return
                    }
                }
                override fun onFailure(call: Call<Food>, t: Throwable) {
                    Log.d("Food delete error",t.message.toString())
                }
            })
        }
    }

    fun observefoodLiveData(): LiveData<Food> {
        return foodLiveData
    }
}