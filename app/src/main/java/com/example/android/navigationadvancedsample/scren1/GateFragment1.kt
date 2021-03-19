package com.example.android.navigationadvancedsample.scren1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.android.navigationadvancedsample.NavControllerContainer
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.java.KoinJavaComponent

class GateFragment1 : Fragment() {


    private lateinit var editorViewModelManual:SharedViewModel
//    by lazy {
//        val store = parentNavController?.value?.getViewModelStoreOwner(R.id.start_nav_graph)?.viewModelStore
//        KoinJavaComponent.getKoin()
//                .getViewModel(ViewModelParameter(SharedViewModel::class, null, null,
//                        null, store!!, null))
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gate1, container, false)

        val parentNavController = (parentFragment?.parentFragment as GateBotomNav).findNavController()
        val store = parentNavController.getViewModelStoreOwner(R.id.start_nav_graph)?.viewModelStore
        editorViewModelManual = KoinJavaComponent.getKoin().getViewModel(ViewModelParameter(SharedViewModel::class, null, null, null, store!!, null))

        view.findViewById<TextView>(R.id.tv_instance).text = "GateFragment1 Shared VM Instance:${editorViewModelManual.hashCode().toString()}"
        view.findViewById<Button>(R.id.btn_enter).setOnClickListener {
//            (activity as MainActivity).findNavController().navigate()
            val startNavController = NavControllerContainer.parentNavController
            startNavController?.navigate(R.id.action_gateBotomNav_to_mainBotNavFragment3)
        }

//        view.findViewById<Button>(R.id.btn_increase).setOnClickListener {
//            editorViewModel.increaseCounter()
//        }
//
//        view.findViewById<Button>(R.id.btn_decrease).setOnClickListener {
//            editorViewModel.decreaseCounter()
//        }
//
//        editorViewModel.counter.observe(viewLifecycleOwner, Observer {
//            view.findViewById<TextView>(R.id.tv_params_list).text = it.toString()
//        })
        return view;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setTitleByInstance()
    }

//    private fun setTitleByInstance() {
//        arguments?.let {
//            val paramName = "instance"
//            val instance = it.getInt(paramName)
//            val textView = view?.findViewById<TextView>(R.id.tv_text);
//            if (instance == 1) textView?.text = editorViewModel.gate1Title else textView?.text = editorViewModel.gate2Title
//
//        }
//    }
}