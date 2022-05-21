package com.example.foodie.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.foodie.model.Food
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class FoodDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: FoodDatabase
    private lateinit var dao: FoodDao

    @Before
    fun setup(){
        database= Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FoodDatabase::class.java
        ).allowMainThreadQueries().build()
        dao=database.foodDao()
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertFood() = runBlockingTest{
        val foodItem = Food("https://foodish-api.herokuapp.com/images/pizza/pizza52.jpg")
        dao.insertFood(foodItem)

        val allFoodItems = dao.getAllFoods().getOrAwaitValue()

        assertThat(allFoodItems).contains(foodItem)
    }


    @Test
    fun getAllFood() = runBlockingTest {
        val foodItem = Food("https://foodish-api.herokuapp.com/images/pizza/pizza52.jpg")
        dao.insertFood(foodItem)

        val allShoppingItems = dao.getAllFoods().getOrAwaitValue()
        assertThat(allShoppingItems).containsExactly(foodItem)
    }

    @Test
    fun deleteFood() = runBlockingTest {
        val foodItem = Food("https://foodish-api.herokuapp.com/images/pizza/pizza52.jpg")
        dao.insertFood(foodItem)
        dao.deleteFood(foodItem)

        val allShoppingItems = dao.getAllFoods().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(foodItem)
    }


}