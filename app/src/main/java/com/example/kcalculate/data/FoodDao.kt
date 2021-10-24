package com.example.kcalculate.data

import androidx.room.*

@Dao
interface FoodDao {
    @Query("SELECT * FROM tb_food WHERE name = :foodName")
    fun loadKcal(foodName: String): Int

    @Insert
    fun insert(pInfo: FoodEntity)

    @Update
    fun update(pInfo: FoodEntity)

    @Delete
    fun delete(pInfo: FoodEntity)

    @Query("DELETE FROM tb_food")
    fun deleteAll()

    @Query("SELECT * FROM tb_food")
    fun getAll(): List<FoodEntity>
}
