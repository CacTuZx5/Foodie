package com.example.foodie.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "foodDB")
data class Food(
    @PrimaryKey
    var image: String
)