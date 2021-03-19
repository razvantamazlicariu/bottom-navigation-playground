package com.example.android.navigationadvancedsample.scren1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.fragment.findNavController
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.viewmodels.EditorBaseFragment
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.java.KoinJavaComponent

class GateFragment2 : EditorBaseFragment() {

    private lateinit var editorViewModelManual:SharedViewModel

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
        val view =  inflater.inflate(R.layout.fragment_gate2, container, false)
        // Inflate the layout for this fragment
        val parentNavController = (parentFragment?.parentFragment as GateBotomNav).findNavController()
        val store = parentNavController.getViewModelStoreOwner(R.id.start_nav_graph)?.viewModelStore
        editorViewModelManual = KoinJavaComponent.getKoin().getViewModel(ViewModelParameter(SharedViewModel::class, null, null, null, store!!, null))

        view.findViewById<TextView>(R.id.tv_instance).text = "GateFragment2 Shared VM Instance:${editorViewModelManual.hashCode().toString()}"

        return view;
    }
}