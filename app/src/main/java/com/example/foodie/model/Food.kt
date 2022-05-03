package com.example.foodie.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Food (
    @PrimaryKey
    var image: String,
)
