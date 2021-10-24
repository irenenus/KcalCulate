package com.example.kcalculate.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.kcalculate.R
import kotlinx.android.synthetic.main.activity_meal.*
import kotlinx.android.synthetic.main.app_bar_back.*


class MealActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        setView()
        onListeners()
    }

    private fun onListeners(){
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setView(){
        // Create an ArrayAdapter using the string array and a default spinner
        val mealAdapter = ArrayAdapter
            .createFromResource(
                this, R.array.meal_array,
                android.R.layout.simple_spinner_item
            )

        // Specify the layout to use when the list of choices appears
        mealAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        ddMeal.adapter = mealAdapter
    }

}