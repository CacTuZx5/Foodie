package com.example.foodie.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_list")
data class Food (

    @PrimaryKey
    var id: Int? = 0,
    var name: String,
    var imageUrl: String,
)
