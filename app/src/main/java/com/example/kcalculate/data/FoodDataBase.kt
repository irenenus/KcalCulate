package com.example.kcalculate.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kcalculate.data.daos.FoodDao
import com.example.kcalculate.data.daos.IngredientsDao
import com.example.kcalculate.data.entities.FoodEntity
import com.example.kcalculate.data.entities.IngredientEntity

@Database(entities = [FoodEntity::class, IngredientEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FoodDataBase : RoomDatabase() {

    abstract fun foodDao(): FoodDao
    abstract fun ingredientDao(): IngredientsDao

    companion object
    {
        //SINGLETON
        var INSTANCE: FoodDataBase? = null

        fun getDatabase(context: Context): FoodDataBase? {
            if (INSTANCE == null) {
                synchronized(FoodDataBase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            FoodDataBase::class.java, "food_db")
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}