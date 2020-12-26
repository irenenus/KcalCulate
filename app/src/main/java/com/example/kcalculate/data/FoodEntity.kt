package com.example.kcalculate.data

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
                       @ColumnInfo(name = "kcal") var kcal: Int)
{
    constructor():this(0,"",0)
    companion object{
        fun getEntity(pInfo: Food): FoodEntity
        {
            val foodEntity: FoodEntity =
                FoodEntity()
            foodEntity.id = pInfo.id
            foodEntity.name = pInfo.name
            foodEntity.kcal = pInfo.kcal
            return foodEntity
        }
    }
}