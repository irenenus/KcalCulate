package com.example.kcalculate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_news.*
import java.text.SimpleDateFormat
import java.util.*
import android.content.Intent
import com.example.kcalculate.R


class NewsFragment : Fragment(){
    companion object {
        fun newInstance(): NewsFragment {
            return NewsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity?.progressBar?.visibility = View.GONE
        super.onViewCreated(view, savedInstanceState)
        setView()
        onListeners()
    }

    private fun setView(){
        val simpleDateFormat = SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH)
        val currentDate = simpleDateFormat.format(Date())
        tvDate.text = currentDate
    }

    private fun onListeners(){
        btnAdd.setOnClickListener {
            val intent = Intent(context, MealActivity::class.java)
            startActivity(intent)
        }
    }
}