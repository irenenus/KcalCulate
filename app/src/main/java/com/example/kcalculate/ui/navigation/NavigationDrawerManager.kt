package com.example.kcalculate.ui.navigation

import android.content.Context
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import com.example.kcalculate.R
import com.example.kcalculate.ui.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*

class NavigationDrawerManager(listener: MainActivity, context: Context):
    NavigationDrawerFragment.OnNavigationDrawerItemClickListener {

    private  val activity: MainActivity = listener
    private val listener: OnSectionListener = listener
    private val navigationDrawerFragment: NavigationDrawerFragment

    init{
        navigationDrawerFragment = activity.supportFragmentManager.findFragmentById(R.id.navigation_drawer) as NavigationDrawerFragment
        navigationDrawerFragment.init(this)
        activity.setSupportActionBar(activity.mainToolbar as Toolbar?)

        activity.navigation_icon.setOnClickListener {
            activity.drawerLayout.openDrawer(GravityCompat.START)
        }

    }

    interface OnSectionListener {
        fun onNews()
        fun onPersonalData()
        fun onRecipes()
    }

    override fun onNewsClick(context: Context) {
        activity.drawerLayout.closeDrawer(GravityCompat.START)
        activity.titleApp.setText(R.string.menu_news)
        listener.onNews()
    }

    override fun onPersonalDataClick(context: Context) {
        activity.drawerLayout.closeDrawer(GravityCompat.START)
        activity.titleApp.setText(R.string.menu_personal_data)
        listener.onPersonalData()
    }

    override fun onRecipesClick(context: Context) {
        activity.drawerLayout.closeDrawer(GravityCompat.START)
        activity.titleApp.setText(R.string.menu_recipes)
        listener.onRecipes()
    }

    fun closeDrawer(withAnimation: Boolean){
        activity.drawerLayout.closeDrawer(GravityCompat.START, withAnimation)
    }

    fun isDrawerOpen(): Boolean {
        return activity.drawerLayout.isDrawerOpen(GravityCompat.START)
    }

}