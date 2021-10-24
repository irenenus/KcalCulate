package com.example.kcalculate.ui

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.kcalculate.NewsFragment
import com.example.kcalculate.R
import com.example.kcalculate.RecipeFragment
import com.example.kcalculate.ui.navigation.NavigationDrawerManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_navigation_drawer.*

open class MainActivity : AppCompatActivity(), NavigationDrawerManager.OnSectionListener {

    private lateinit var navigationDrawerManager: NavigationDrawerManager
    private val fragmentNews = NewsFragment.newInstance()
    private val fragmentPersonalData = PersonalDataFragment.newInstance()
    private val fragmentRecipes = RecipeFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar as Toolbar?)
        navigationDrawerManager =
            NavigationDrawerManager(this, applicationContext)

        setFragment(fragmentNews)
    }

    // view of the selected items from the navigation drawer
    private fun selectMenuItem(view: View, tvSelected: TextView, tvDisabled1: TextView, tvDisabled2: TextView) {
        selectorNews.visibility = View.INVISIBLE
        selectorPersonalData.visibility = View.INVISIBLE
        selectorRecipe.visibility = View.INVISIBLE

        view.visibility = View.VISIBLE

        tvSelected.isActivated = true
        tvSelected.setTypeface(Typeface.DEFAULT, Typeface.BOLD)
        tvDisabled1.isActivated = false
        tvDisabled1.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
        tvDisabled2.isActivated = false
        tvDisabled2.setTypeface(Typeface.DEFAULT, Typeface.NORMAL)
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer, fragment, fragment.javaClass.canonicalName)
            .commit()
    }

    override fun onNews(){
        selectMenuItem(selectorNews, tvNews, tvPersonalData, tvRecipe)
        setFragment(fragmentNews)
    }

    override fun onPersonalData() {
        selectMenuItem(selectorPersonalData, tvPersonalData, tvNews, tvRecipe)
        setFragment(fragmentPersonalData)
    }

    override fun onRecipes() {
        selectMenuItem(selectorRecipe, tvRecipe, tvPersonalData, tvNews)
        setFragment(fragmentRecipes)
    }

    override fun onBackPressed() {
        if(navigationDrawerManager.isDrawerOpen()){
            navigationDrawerManager.closeDrawer(true)
        }
        else{
            finish()
        }
    }

}
