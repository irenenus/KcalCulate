package com.example.kcalculate.repository

import android.content.Context
import android.os.AsyncTask
import com.example.kcalculate.data.FoodDao
import com.example.kcalculate.data.FoodDataBase
import com.example.kcalculate.data.FoodEntity

class FoodRepository(context: Context) {

    var db: FoodDao? = FoodDataBase.getDatabase(context)?.foodDao()


    //Fetch All the Food Entities
    fun getAllFood(): List<FoodEntity>? {
        return db?.getAll()
    }

    // Insert new food
    fun insertFood(foodEntity: FoodEntity) {
        db?.let { InsertAsyncTask(it).execute(foodEntity) }
    }

    // update food
    fun updateFood(foodEntity: FoodEntity) {
        db?.update(foodEntity)
    }

    // Delete food
    fun deleteFood(foodEntity: FoodEntity) {
        db?.delete(foodEntity)
    }

    private class InsertAsyncTask internal constructor(private val foodDao: FoodDao) :
        AsyncTask<FoodEntity, Void, Void>() {

        override fun doInBackground(vararg params: FoodEntity): Void? {
            foodDao.insert(params[0])
            return null
        }
    }
}