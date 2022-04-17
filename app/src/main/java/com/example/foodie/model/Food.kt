package com.example.foodie.model

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Food (
    @PrimaryKey
    var id: Int? = 0,
    var name: String,
    var imageUrl: String,
)
