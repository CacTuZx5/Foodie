package com.example.foodie.ui.foodlist

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.foodie.data.FoodRepository
import com.example.foodie.model.Food
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.junit.MockitoRule

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    @Rule
    @JvmField
    var initRule:MockitoRule=MockitoJUnit.rule()
    private val application = Mockito.mock(Application::class.java)

    private val foodlistRepository:FoodRepository=Mockito.mock(FoodRepository::class.java)
    private var mainViewModel:MainViewModel?=null

    private lateinit var foods:ArrayList<Food>


    @Before
    fun setUp() {
        foods=ArrayList()
        foods.add(Food("https://foodish-api.herokuapp.com/images/pizza/pizza72.jpg"))
        foods.add(Food("https://foodish-api.herokuapp.com/images/pasta/pasta11.jpg"))

        Mockito.doReturn(foods).`when`(foodlistRepository).getAllFoods()
        //mainViewModel=MainViewModel01(foodlistRepository)
    }

    @Test
    fun createNewFood() {
    }

    @Test
    fun getRandomFood() {

    }

    @Test
    fun deleteFood() {
    }

    @Test
    fun observefoodLiveData() {
    }
}