package com.example.kcalculate.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FoodEntity::class], version = 1, exportSchema = false)
abstract class FoodDataBase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

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