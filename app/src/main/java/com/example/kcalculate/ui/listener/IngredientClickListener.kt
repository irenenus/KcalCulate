package com.example.kcalculate.ui.listener

import com.example.kcalculate.data.entities.IngredientEntity

interface IngredientClickListener {
        fun onItemClick(ingredient : IngredientEntity)
}