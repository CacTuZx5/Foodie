package com.example.foodie.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.foodie.model.Food

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(foods: Food)

    @Query("SELECT * FROM Food WHERE image = :id")
    suspend fun getFood(id: String): Food?

    @Query("SELECT * FROM Food")
    fun getFoodList(): List<Food>

    @Query("DELETE FROM Food WHERE image =:id_")
    fun deleteFood(id_:String)
}