package com.example.kcalculate.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kcalculate.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
    }
}
