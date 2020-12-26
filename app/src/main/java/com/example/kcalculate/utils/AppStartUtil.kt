package com.example.kcalculate.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.content.pm.PackageInfoCompat
import com.example.kcalculate.enums.AppStart
import com.example.kcalculate.R
import com.example.kcalculate.utils.Constants.Companion.CLASS_TAG
import com.example.kcalculate.utils.Constants.Companion.LAST_APP_VERSION
import com.example.kcalculate.utils.Constants.Companion.PREFS_FILENAME

class AppStartUtil (private  val context: Context){

    private val sharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    val isFirstTimeLaunch: Boolean
        get() = checkAppStart(context) == AppStart.FIRST_TIME

    private fun checkAppStart(context: Context) : AppStart {
        val pInfo: PackageInfo
        var appStart = AppStart.NORMAL
        try {
            pInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            val lastVersionCode = sharedPreferences.getLong(LAST_APP_VERSION, -1L)
            val currentVersionCode = PackageInfoCompat.getLongVersionCode(pInfo)
            appStart = checkAppStart(currentVersionCode, lastVersionCode)

            //Update version in preferences
            sharedPreferences.edit().putLong(LAST_APP_VERSION, currentVersionCode).apply()
        } catch (e: PackageManager.NameNotFoundException){
            Log.w(
                CLASS_TAG,
                context.getString(R.string.error_message_intro)
            )
        }

        return appStart
    }

    private fun checkAppStart(currentVersionCode: Long, lastVersionCode: Long): AppStart {
        return when {
            lastVersionCode == -1L -> {
                AppStart.FIRST_TIME
            }
            lastVersionCode < currentVersionCode -> {
                AppStart.FIRST_TIME_VERSION
            }
            else -> {
                AppStart.NORMAL
            }
        }
    }
}