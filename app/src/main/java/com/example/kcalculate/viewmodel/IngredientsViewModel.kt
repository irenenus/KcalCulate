package com.example.kcalculate.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kcalculate.data.entities.IngredientEntity
import com.example.kcalculate.network.AppResult
import com.example.kcalculate.repository.IngredientsRepository
import kotlinx.coroutines.launch


class IngredientsViewModel(private val repository : IngredientsRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val ingredientsList = MutableLiveData<List<IngredientEntity>?>()
    val showError = MutableLiveData<String?>()

    fun getIngredientsList() {
        showLoading.set(true)
        viewModelScope.launch {
            val result =  repository.getIngredients()

            showLoading.set(false)
            when (result) {
                is AppResult.Success -> {
                    ingredientsList.value = result.successData.meals
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}