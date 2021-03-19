package com.example.android.navigationadvancedsample

import com.example.android.navigationadvancedsample.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val koinModule = module {
    viewModel { (timelineId: Long) -> SharedViewModel(timelineId) }

}