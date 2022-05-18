package com.example.foodie.ui.foodlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.di.NetworkModule
import com.example.foodie.model.Foodmodel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainViewModel():ViewModel() {
    private var foodLiveData= MutableLiveData<Foodmodel>()
    private var mock = true

fun createNewFood(f:Foodmodel){
    if (mock){
        foodLiveData.value=f
        Log.d("creating new item","${f.image}")
    }
    else {
        NetworkModule.api.createNewFood(f).enqueue(object : Callback<Foodmodel> {
            override fun onResponse(call: Call<Foodmodel>, response: Response<Foodmodel>) {
                if (response.body()!=null){
                    Log.d("delete","${response.body()}")
                }
                else{
                    return
                }

            }

            override fun onFailure(call: Call<Foodmodel>, t: Throwable) {
                Log.d("Food creating error",t.message.toString())
            }
        })
    }


}

    fun getRandomFood(){
        NetworkModule.api.getFoods().enqueue(object : Callback<Foodmodel> {
            override fun onResponse(call: Call<Foodmodel>, response: Response<Foodmodel>) {
                if (response.body()!=null){
                    val fooditem: Foodmodel = response.body()!!

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

            override fun onFailure(call: Call<Foodmodel>, t: Throwable) {
                Log.d("Food list fragment error",t.message.toString())
            }
        })
    }

    fun deleteFood(f:Foodmodel){
        if (mock){

        }
        else {
            NetworkModule.api.deleteFood(f).enqueue(object : Callback<Foodmodel> {
                override fun onResponse(call: Call<Foodmodel>, response: Response<Foodmodel>) {
                    if (response.body()!=null){
                        Log.d("delete","${response.body()}")
                    }
                    else{
                        return
                    }

                }
                override fun onFailure(call: Call<Foodmodel>, t: Throwable) {
                    Log.d("Food delete error",t.message.toString())
                }
            })
        }

    }


    fun observefoodLiveData(): LiveData<Foodmodel> {
        return foodLiveData
    }
}