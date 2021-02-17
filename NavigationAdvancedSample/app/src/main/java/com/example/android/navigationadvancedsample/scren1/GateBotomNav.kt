package com.example.android.navigationadvancedsample.scren1

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDirections
import androidx.navigation.ui.NavigationUI
import com.example.android.navigationadvancedsample.R
import com.example.android.navigationadvancedsample.setupWithNavController
import com.example.android.navigationadvancedsample.setupWithNavControllerWithLastSelectedTabRestore
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.Exception

class GateBotomNav : Fragment(){

    object GateBottomNavInstance {
        var owner: ViewModelStoreOwner? = null
    }

    private val LAST_SELECTED_ITEM_ID = "lastSelectedItemId"

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_gate_botom_nav, container, false)
        setupBottomNavigationBar(view, arguments?.getInt(LAST_SELECTED_ITEM_ID) ?: -1)

        // Because we don't have an Activity as parent for the NavControllerFragments we can't use activityViewModels
        // What i came up with, is storing the ViewModelStoreOwner
        GateBottomNavInstance.owner = this
        val viewModel = ViewModelProvider( GateBottomNavInstance.owner as ViewModelStoreOwner).get(SharedViewModel::class.java)
        return view
    }

    override fun onPause() {
        super.onPause()

        // Save lastSelectedItemId in order to restore the fragment when the user comes back to it
        val tab = view?.findViewById<BottomNavigationView>(R.id.fragment_gate_bottom_nav)?.selectedItemId ?: -1
        arguments = bundleOf(Pair(LAST_SELECTED_ITEM_ID, tab))
    }

    private fun setupBottomNavigationBar(view: View, restoredTabId: Int = -1) {
        val navGraphIds = listOf(R.navigation.gate1, R.navigation.gate2)

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