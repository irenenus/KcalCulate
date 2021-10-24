package com.example.kcalculate.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.kcalculate.model.Food

/*
db table
 */
@Entity(tableName = "tb_food")
data class FoodEntity (@PrimaryKey(autoGenerate = true) var id:Int,
                       @ColumnInfo(name = "name") var name: String,
                       @ColumnInfo(name = "kcal") var kcal: Int,
                       @ColumnInfo(name = "carbohydrate") var carb: Int,
                       @ColumnInfo(name = "fat") var fat: Int,
                       @ColumnInfo(name = "protein") var protein: Int,
                       @ColumnInfo(name = "sugar") var sugar: Int,
                       @ColumnInfo(name = "salt") var salt: Int,
                       @ColumnInfo(name = "fiber") var fiber: Int)
{
    constructor():this(0,"",0,0,0,0,0,0,0)
    companion object{
        fun getEntity(pInfo: Food): FoodEntity
        {
            val foodEntity: FoodEntity =
                FoodEntity()
            foodEntity.id = pInfo.id
            foodEntity.name = pInfo.name
            foodEntity.kcal = pInfo.kcal
            foodEntity.carb = pInfo.carbohydrate
            foodEntity.fat = pInfo.fat
            foodEntity.protein = pInfo.protein
            foodEntity.sugar = pInfo.sugar
            foodEntity.salt = pInfo.salt
            foodEntity.fiber = pInfo.fiber
            return foodEntity
        }
    }
}