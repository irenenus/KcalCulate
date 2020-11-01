package com.example.kcalculate.ui.intro.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter;

class IntroPageAdapter(manager: FragmentActivity) : FragmentStateAdapter(manager){

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
    }

    val fragmentList = mutableListOf<Fragment>()
}