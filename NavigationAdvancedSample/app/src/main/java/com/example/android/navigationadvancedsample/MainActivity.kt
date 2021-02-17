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
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment)
        // Set's up the Toolbar. Can be removed if we implement a Toolbar for each Fragment
        setupActionBarWithNavController(navController)

        // Saving the parent NavController for navigation between the 2 fragments that contain a bottomNvaigationView
        NavControllerContainer.parentNavController = navController
    }

    override fun onSupportNavigateUp(): Boolean {
        // Easy fix to the navigation problem.
        // For some reason, onBackPressed() works fine and follows the normal back flow
        onBackPressed()
        return true
    }
}
