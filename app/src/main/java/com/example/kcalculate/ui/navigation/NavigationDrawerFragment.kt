package com.example.kcalculate.ui.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kcalculate.R
import kotlinx.android.synthetic.main.fragment_navigation_drawer.*

class NavigationDrawerFragment : Fragment() {
    private lateinit var listener: OnNavigationDrawerItemClickListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false)
    }

    fun init(navigationDrawerManager: NavigationDrawerManager) {
        this.listener = navigationDrawerManager
        context?.let { context ->
            tvNews.setOnClickListener {
                (listener as NavigationDrawerManager).onNewsClick(
                    context
                )
            }

            tvPersonalData.setOnClickListener {
                (listener as NavigationDrawerManager).onPersonalDataClick(
                    context
                )
            }
            tvRecipe.setOnClickListener {
                (listener as NavigationDrawerManager).onRecipesClick(
                    context
                )
            }

        }

    }

    interface OnNavigationDrawerItemClickListener {
        fun onNewsClick(context: Context)
        fun onPersonalDataClick(context: Context)
        fun onRecipesClick(context: Context)
    }
}