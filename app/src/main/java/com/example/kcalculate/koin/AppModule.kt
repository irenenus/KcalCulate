package com.example.kcalculate.koin

import android.app.Application
import androidx.room.Room
import com.example.kcalculate.data.FoodDao
import com.example.kcalculate.data.FoodDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): FoodDataBase? {
        return FoodDataBase.getDatabase(application.applicationContext)
    }

    fun provideCountriesDao(database: FoodDataBase): FoodDao {
        return  database.foodDao()
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}
