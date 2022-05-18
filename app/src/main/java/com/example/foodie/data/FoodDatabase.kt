package com.example.foodie.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.foodie.model.Food

@Database(entities = [Food::class], version = 1)
abstract class FoodDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object{
        @Volatile
        var INSTANCE:FoodDatabase?=null

        @Synchronized
        fun getInstance(context: Context):FoodDatabase{
            if (INSTANCE==null)
            {
                INSTANCE=Room.databaseBuilder(
                    context,
                    FoodDatabase::class.java,"food.db").
                fallbackToDestructiveMigration().
                build()
            }
            return INSTANCE as FoodDatabase
        }
    }
}
