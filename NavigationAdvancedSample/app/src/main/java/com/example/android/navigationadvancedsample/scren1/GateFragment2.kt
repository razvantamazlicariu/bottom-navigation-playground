package com.example.android.navigationadvancedsample.scren1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel

class GateFragment2 : Fragment() {

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)


        arguments?.let {
            val paramName = "gate1Param"
            val firstParam = it.getString(paramName)
            view?.findViewById<TextView>(R.id.tv_params_list)?.text = "$paramName - $firstParam"
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_gate2, container, false)

        val viewModel = ViewModelProvider( GateBotomNav.GateBottomNavInstance.owner as ViewModelStoreOwner).get(SharedViewModel::class.java)

        return view;
    }
}