package com.example.kcalculate.repository

import com.example.kcalculate.model.IngredientsResponse
import com.example.kcalculate.network.AppResult

interface IngredientsRepository {
    suspend fun getIngredients() : AppResult<IngredientsResponse>
}