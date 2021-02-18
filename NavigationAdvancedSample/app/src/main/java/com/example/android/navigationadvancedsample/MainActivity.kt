/*
 * Copyright 2019, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigationadvancedsample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.navigation.NavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        // Easy fix to the navigation problem.
        // For some reason, onBackPressed() works fine and follows the normal back flow
        onBackPressed()
        return true
    }

    override fun onPause() {
        super.onPause()

//        val tab = findViewById<BottomNavigationView>(R.id.fragment_gate_bottom_nav)?.selectedItemId ?: -1
//        arguments = bundleOf(Pair(LAST_SELECTED_ITEM_ID, tab))
    }

    private fun setupBottomNavigationBar(restoredTabId: Int = -1) {
        val navGraphIds = listOf(R.navigation.gate1, R.navigation.gate2, R.navigation.gate3)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.fragment_gate_bottom_nav)
        val controller = bottomNavigationView.setupWithNavControllerWithLastSelectedTabRestore(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_container,
                intent = intent,
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
    }
}
