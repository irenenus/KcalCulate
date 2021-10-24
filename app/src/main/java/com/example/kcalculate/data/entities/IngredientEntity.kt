package com.example.kcalculate.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Ingredients")
@Parcelize
class IngredientEntity (
    @PrimaryKey(autoGenerate = false) val idIngredient: String,
    val strIngredient: String,
    val strDescription: String
) : Parcelable