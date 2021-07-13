package com.agiletech.darkmodetest.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.agiletech.darkmodetest.App
import com.agiletech.darkmodetest.R
import com.google.android.material.button.MaterialButton

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        var view = inflater.inflate(R.layout.main_fragment, container, false)
        var day : MaterialButton =  view.findViewById(R.id.day)
        var night: MaterialButton =  view.findViewById(R.id.night)
        var auto: MaterialButton =  view.findViewById(R.id.auto)
        var nightBtn: Button =  view.findViewById(R.id.night_btn)

        day.setOnClickListener {
            (activity?.application as App).preferenceRepository.darkModeSetting =1
        }

        night.setOnClickListener {
            (activity?.application as App).preferenceRepository.darkModeSetting = 2
        }

        auto.setOnClickListener {
            (activity?.application as App).preferenceRepository.darkModeSetting = 3
        }

        nightBtn.setOnClickListener {
            (activity?.application as App).preferenceRepository.darkModeSetting = 2
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    }

}