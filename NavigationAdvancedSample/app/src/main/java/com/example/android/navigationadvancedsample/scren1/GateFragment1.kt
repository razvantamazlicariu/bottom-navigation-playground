package com.example.android.navigationadvancedsample.scren1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.android.navigationadvancedsample.NavControllerContainer
import com.example.android.navigationadvancedsample.R

class GateFragment1 : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_gate1, container, false)
        view.findViewById<Button>(R.id.btn_enter).setOnClickListener {
//            (activity as MainActivity).findNavController().navigate()
            val startNavController = NavControllerContainer.parentNavController
            startNavController?.navigate(R.id.action_gateBotomNav_to_mainBotNavFragment3)
        }
        arguments?.let {
            val paramName = "gate1Param"
            val firstParam = it.getString(paramName)
            view.findViewById<TextView>(R.id.tv_params_list).text = "$paramName - $firstParam"
        }

        return view;
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)

        arguments?.let {
            val paramName = "gate1Param"
            val firstParam = it.getString(paramName)
            view?.findViewById<TextView>(R.id.tv_params_list)?.text = "$paramName - $firstParam"
        }
    }

}