package com.example.android.navigationadvancedsample.viewmodels

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.android.navigationadvancedsample.R
import org.koin.androidx.viewmodel.ViewModelParameter
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.Qualifier
import org.koin.java.KoinJavaComponent

abstract class EditorBaseFragment : Fragment() {
    var timelineId: Long = -1
    protected val editorViewModel by sharedGraphViewModel<SharedViewModel>(R.id.start_nav_graph) { parametersOf(timelineId) }
}

inline fun <reified VM : ViewModel> Fragment.sharedGraphViewModel(
        @IdRes navGraphId: Int,
        qualifier: Qualifier? = null,
        noinline parameters: ParametersDefinition? = null
) = lazy {
    val store = findNavController().getViewModelStoreOwner(navGraphId).viewModelStore
    KoinJavaComponent.getKoin().getViewModel(ViewModelParameter(VM::class, qualifier, parameters, null, store, null))
}
