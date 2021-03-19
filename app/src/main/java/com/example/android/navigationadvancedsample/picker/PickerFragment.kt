package com.example.android.navigationadvancedsample.picker

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.setupWithNavControllerWithLastSelectedTabRestore
import com.google.android.material.bottomnavigation.BottomNavigationView

class PickerFragment : Fragment() {
    private lateinit var viewModel: PickerViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PickerViewModel::class.java)
    }

    private val LAST_SELECTED_ITEM_ID = "lastSelectedItemId"

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.picker_fragment, container, false)
        setupBottomNavigationBar(view, arguments?.getInt(LAST_SELECTED_ITEM_ID) ?: -1)

        // Because we don't have an Activity as parent for the NavControllerFragments we can't use activityViewModels
        // What i came up with, is storing the ViewModelStoreOwner
        //GateBottomNavInstance.owner = this
        //val viewModel = ViewModelProvider( GateBottomNavInstance.owner as ViewModelStoreOwner).get(SharedViewModel::class.java)
        return view
    }

    override fun onPause() {
        super.onPause()

        // Save lastSelectedItemId in order to restore the fragment when the user comes back to it
        val tab = view?.findViewById<BottomNavigationView>(R.id.fragment_gate_bottom_nav)?.selectedItemId ?: -1
        arguments = bundleOf(Pair(LAST_SELECTED_ITEM_ID, tab))
    }

    private fun setupBottomNavigationBar(view: View, restoredTabId: Int = -1) {
        val navGraphIds = listOf(R.navigation.media_navigation, R.navigation.text_navigation, R.navigation.music_navigation)

        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.fragment_gate_bottom_nav)
        val controller = bottomNavigationView.setupWithNavControllerWithLastSelectedTabRestore(
                navGraphIds = navGraphIds,
                fragmentManager = childFragmentManager,
                containerId = R.id.nav_host_container,
                intent = requireActivity().intent,
                lastSelectedTabId = restoredTabId
        )

        controller.value?.addOnDestinationChangedListener(
                fun(controller: NavController, destination: NavDestination, arguments: Bundle?) {
                    if (destination.id == R.id.gateFragment1) {
                        val navArgument2 = NavArgument.Builder().setDefaultValue("Hello").build()
                        destination.addArgument("navArg1", navArgument2)
                    }
                }
        )

        currentNavController = controller
    }
}