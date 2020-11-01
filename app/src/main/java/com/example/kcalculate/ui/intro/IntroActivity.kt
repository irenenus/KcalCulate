package com.example.kcalculate.ui.intro

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.kcalculate.utils.Constants
import com.example.kcalculate.ui.MainActivity
import com.example.kcalculate.R
import com.example.kcalculate.ui.intro.adapter.IntroPageAdapter
import kotlinx.android.synthetic.main.intro_activity.*

class IntroActivity: FragmentActivity() {

    private lateinit var pagerAdapter: IntroPageAdapter
    private val dots = ArrayList<ImageView>()
    private var currentPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intro_activity)

        setupPageAdapter()
        setUpIndicatorDots()
        setUpListeners()
    }

    private fun setupPageAdapter(){
        pagerAdapter = IntroPageAdapter(this)
        pagerAdapter.addFragment(Slide1Fragment())
        pagerAdapter.addFragment(Slide2Fragment())
        pagerAdapter.addFragment(Slide3Fragment())

        viewPager.adapter = pagerAdapter
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                currentPage = position
                updateIndicatorDots(position)
                if(currentPage < pagerAdapter.fragmentList.size - 1){
                    (btnNext as View).visibility = View.VISIBLE
                    btnFinish.visibility = View.GONE

                } else {
                    (btnNext as View).visibility = View.GONE
                    btnFinish.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setUpListeners(){
        btnNext.setOnClickListener {
            if(currentPage < pagerAdapter.fragmentList.size - 1){
                viewPager.setCurrentItem(++currentPage, true)
            }
        }
        btnFinish.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra(Constants.FIRST_TIME_LAUNCH ,true)

            startActivity(intent)
            finish()
        }
    }

    private fun setUpIndicatorDots(){
        for (i in 0 until pagerAdapter.itemCount){
            val dot = ImageView(this)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(10,0,10,0)
            dot.layoutParams = params
            dots.add(dot)
            dots[i].setImageResource(R.drawable.ic_dot_active_white)
            layoutDots.addView(dots[i])
        }

        if(dots.isNotEmpty()){
            dots[0].setImageResource(R.drawable.ic_dot_active_white)
        }
    }

    private fun updateIndicatorDots(position: Int){
        for((index, dot) in dots.withIndex()){
            if(index == position){
                dot.setImageResource(R.drawable.ic_dot_active_white)
            } else {
                dot.setImageResource(R.drawable.ic_dot_inactive_white)
            }
        }
    }
}