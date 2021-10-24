package com.example.kcalculate.network

import com.example.kcalculate.model.IngredientsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(".")
    suspend fun getIngredientsList(): Response<IngredientsResponse>
}