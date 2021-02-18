package com.example.android.navigationadvancedsample.scren1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel

class GateFragment1 : Fragment() {

    private val viewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gate1, container, false)
//        val viewModel = ViewModelProvider( GateBotomNav.GateBottomNavInstance.owner as ViewModelStoreOwner).get(SharedViewModel::class.java)

        view.findViewById<Button>(R.id.btn_enter).setOnClickListener {
//            (activity as MainActivity).findNavController().navigate()
            findNavController().navigate(R.id.action_gateFragment1_to_secondBottomNavigationActivity)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitleByInstance()
    }

    private fun setTitleByInstance() {
        arguments?.let {
            val paramName = "instance"
            val instance = it.getInt(paramName)
            val textView = view?.findViewById<TextView>(R.id.tv_text);
            if (instance == 1) textView?.text = viewModel.gate1Title else textView?.text = viewModel.gate2Title

        }
    }
}