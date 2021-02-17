package com.example.android.navigationadvancedsample.scren1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.android.navigationadvancedsample.NavControllerContainer
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel

class GateFragment1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_gate1, container, false)
        val viewModel = ViewModelProvider( GateBotomNav.GateBottomNavInstance.owner as ViewModelStoreOwner).get(SharedViewModel::class.java)


        view.findViewById<Button>(R.id.btn_enter).setOnClickListener {
//            (activity as MainActivity).findNavController().navigate()
            val startNavController = NavControllerContainer.parentNavController
            startNavController?.navigate(R.id.action_gateBotomNav_to_mainBotNavFragment3)
        }

        view.findViewById<Button>(R.id.btn_increase).setOnClickListener {
            viewModel.increaseCounter()
        }

        view.findViewById<Button>(R.id.btn_decrease).setOnClickListener {
            viewModel.decreaseCounter()
        }

        viewModel.counter.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.tv_params_list).text = it.toString()
        })
        return view;
    }
}