package com.example.foodie.ui.about

import com.example.foodie.data.FoodDao
import androidx.annotation.WorkerThread
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AboutRepository @Inject constructor(
private val foodDao: FoodDao
) {
}