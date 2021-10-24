package com.example.kcalculate.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kcalculate.data.entities.IngredientEntity

@Dao
interface IngredientsDao {

        @Query("SELECT * FROM Ingredients")
        fun findAll(): List<IngredientEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun add(users: List<IngredientEntity>)

}