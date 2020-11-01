package com.example.kcalculate.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kcalculate.R
import com.example.kcalculate.ui.intro.IntroActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)

        checkIsFirstTime()
    }

    private fun checkIsFirstTime(){
        val intent = Intent(this, IntroActivity::class.java)

        startActivity(intent)
        finish()
    }
}