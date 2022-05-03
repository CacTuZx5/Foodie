package com.example.foodie.di

import android.app.Application
import androidx.room.Room
import com.example.foodie.R
import com.example.foodie.data.FoodDao
import com.example.foodie.data.FoodDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    @Provides
    @Singleton
    fun provideFoodDatabase(application: Application): FoodDatabase {
        return Room
            .databaseBuilder(
                application,
                FoodDatabase::class.java,
                application.getString(R.string.database)
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideFoodDao(database: FoodDatabase): FoodDao {
        return database.foodDao()
    }
}