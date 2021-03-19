package com.example.android.navigationadvancedsample.picker.media

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.scren1.GateBotomNav
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.java.KoinJavaComponent

class MediaFragment : Fragment() {

    private lateinit var editorViewModelManual: SharedViewModel
    private lateinit var viewModel: MediaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.media_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MediaViewModel::class.java)

        val parentNavController = (parentFragment?.parentFragment?.parentFragment?.parentFragment?.parentFragment?.parentFragment as GateBotomNav).findNavController()
        val store = parentNavController.getViewModelStoreOwner(R.id.start_nav_graph)?.viewModelStore
        editorViewModelManual = KoinJavaComponent.getKoin().getViewModel(ViewModelParameter(SharedViewModel::class, null, null, null, store!!, null))

        view?.findViewById<TextView>(R.id.tv_instance)?.text = "MediaFragment Shared VM Instance:${editorViewModelManual.hashCode().toString()}"

    }

}