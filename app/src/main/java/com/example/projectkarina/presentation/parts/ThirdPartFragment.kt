package com.example.projectkarina.presentation.parts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectkarina.R

class ThirdPartFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third_part, container, false)
    }

    companion object {
        fun newInstance() = ThirdPartFragment()
    }
}