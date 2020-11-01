package com.example.kcalculate.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kcalculate.R
import com.example.kcalculate.ui.intro.IntroActivity
import com.example.kcalculate.utils.AppStartUtil

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_activity)

        checkIsFirstTime()
    }

    private fun checkIsFirstTime(){
        val isFirstTime = AppStartUtil(this).isFirstTimeLaunch

        if(isFirstTime){
            val intent = Intent(this, IntroActivity::class.java)
            startActivity(intent)
            finish()
        }
        else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}