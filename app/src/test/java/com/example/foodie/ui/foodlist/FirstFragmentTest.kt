package com.example.foodie.ui.foodlist

import androidx.recyclerview.widget.GridLayoutManager
import com.example.foodie.databinding.FragmentHomeBinding
import com.example.foodie.model.Food
import junit.framework.Assert
import org.junit.Before
import org.junit.Test

class FirstFragmentTest {
    lateinit var foodAdapter:FoodItemAdapter
    private lateinit var myfoodList:ArrayList<Food>
    private val f1: Food= Food("https://foodish-api.herokuapp.com/images/pizza/pizza72.jpg")
    private lateinit var binding: FragmentHomeBinding


    @Before
    fun setUp() {
        binding.recViewFoods.apply {
            layoutManager= GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
            adapter=foodAdapter
        }
        foodAdapter = FoodItemAdapter()
        myfoodList=ArrayList<Food>()

    }

    @Test
    fun getActivity() {
    }

    @Test
    fun setFoodlist() {

        myfoodList.add(f1)
        foodAdapter.setfoodList(myfoodList)
        Assert.assertEquals(foodAdapter.getfoodList(), myfoodList)

    }

    @Test
    fun setActivity() {
    }

    @Test
    fun onCreate() {
    }

    @Test
    fun onCreateView() {
    }

    @Test
    fun onViewCreated() {
    }

    @Test
    fun onDestroyView() {
    }
}