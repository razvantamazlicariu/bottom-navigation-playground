package com.example.android.navigationadvancedsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.navGraphViewModels
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference

class MainBotNavFragment : Fragment() {
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_main_bot_nav, container, false)
        setupBottomNavigationBar(view)

        return view;
    }

    private fun setupBottomNavigationBar(view: View) {
        val navGraphIds = listOf(R.navigation.home, R.navigation.list, R.navigation.form)
        val bottomNavigationView = view.findViewById<BottomNavigationView>(R.id.bottom_nav)
        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = childFragmentManager,
                containerId = R.id.nav_host_container,
                intent = activity!!.intent
        )

        currentNavController = controller
    }
}