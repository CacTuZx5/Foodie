package com.example.foodie.di


import com.example.foodie.data.FoodDao
import com.example.foodie.network.FoodService
import com.example.foodie.ui.foodlist.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        foodService: FoodService,
        foodDao: FoodDao
    ): MainRepository {
        return MainRepository(foodService, foodDao)
    }
}