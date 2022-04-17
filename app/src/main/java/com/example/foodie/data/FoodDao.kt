package com.example.foodie.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodie.model.Food


@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodList(foods: List<Food>)

    @Query("SELECT * FROM Food WHERE id = :id_")
    suspend fun getFood(id_: Long): Food?

    @Query("SELECT * FROM Food")
    suspend fun getFoodList(): List<Food>
}