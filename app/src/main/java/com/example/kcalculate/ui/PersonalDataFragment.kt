package com.example.kcalculate.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kcalculate.R

class PersonalDataFragment : Fragment(){
    companion object {
        fun newInstance(): PersonalDataFragment {
            return PersonalDataFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal_data, container, false)
    }
}