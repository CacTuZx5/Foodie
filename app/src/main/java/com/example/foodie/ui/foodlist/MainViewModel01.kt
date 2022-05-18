package com.example.foodie.ui.foodlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.di.NetworkModule
import com.example.foodie.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel01 @Inject constructor(
    mainRepository: MainRepository
) : ViewModel() {
    private var foodLiveData=MutableLiveData<Food>()


    fun getRandomFood(){
        NetworkModule.api.getFoods().enqueue(object : Callback<Food> {
            override fun onResponse(call: Call<Food>, response: Response<Food>) {
                if (response.body()!=null){
                    val fooditem: Food = response.body()!!

                    foodLiveData.value=fooditem
                    val list = fooditem.image.split("/")
                    val name=list[4]

                    Log.d("TEST","food name ${fooditem.image} ")
                    Log.d("food name","${name}")
                }
                else{
                    return
                }

            }

            override fun onFailure(call: Call<Food>, t: Throwable) {
                Log.d("Food list fragment",t.message.toString())
            }
        })
    }
    fun observefoodLiveData():LiveData<Food>{
        return foodLiveData
    }
}