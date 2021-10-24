package com.example.kcalculate.repository.implementation

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.example.kcalculate.data.daos.IngredientsDao
import com.example.kcalculate.data.entities.IngredientEntity
import com.example.kcalculate.model.IngredientsResponse
import com.example.kcalculate.network.ApiService
import com.example.kcalculate.network.AppResult
import com.example.kcalculate.network.handleApiError
import com.example.kcalculate.network.handleSuccess
import com.example.kcalculate.repository.IngredientsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientsRepositoryImpl(
    private val api: ApiService,
    private val dao: IngredientsDao
) : IngredientsRepository {

    override suspend fun getIngredients(): AppResult<IngredientsResponse> {
        return try {
            try {
                val response = api.getIngredientsList()
                if (response.isSuccessful) {
                    //save the data
                    response.body()?.let {
                        withContext(Dispatchers.IO) { dao.add(it.meals) }
                    }
                    handleSuccess(response)
                } else {
                    handleApiError(response)
                }
            } catch (e: Exception) {
                AppResult.Error(e)
            }
        } catch (e: Exception) {
            //check in db if the data exists
            val data = getIngredientsDataFromCache()
            return if (data.isNotEmpty()) {
                Log.d(TAG, "from db")
                AppResult.Success(IngredientsResponse(data))
            }
            else{
                AppResult.Error(e)
            }
        }
    }

    private suspend fun getIngredientsDataFromCache(): List<IngredientEntity> {
        return withContext(Dispatchers.IO) {
            dao.findAll()
        }
    }
}
