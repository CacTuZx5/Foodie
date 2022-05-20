package com.example.foodie.ui.foodlist

import com.example.foodie.model.Food
import junit.framework.Assert.assertEquals
import org.junit.Test

class FoodItemAdapterTest {
    private var foodList=ArrayList<Food>()
    private var myfoodList=ArrayList<Food>()


    val f1:Food = Food("https://foodish-api.herokuapp.com/images/pizza/pizza72.jpg")
    val f2:Food = Food("https://foodish-api.herokuapp.com/images/pasta/pasta11.jpg")

    @Test
    fun getOnItemClick() {
    }

    @Test
    fun setOnItemClick() {
    }

    @Test
    fun setfoodList() {

        myfoodList.add(f1)
        this.foodList=myfoodList as ArrayList<Food>
        assertEquals(myfoodList,foodList)


    }

    @Test
    fun onCreateViewHolder() {
    }

    @Test
    fun onBindViewHolder() {
    }

    @Test
    fun getItemCount() {
    }
}