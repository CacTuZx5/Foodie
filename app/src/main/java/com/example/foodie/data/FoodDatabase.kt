package com.example.foodie.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.foodie.model.Food

@Database(entities = [Food::class], version = 1, exportSchema = true)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun posterDao(): FoodDao
}
