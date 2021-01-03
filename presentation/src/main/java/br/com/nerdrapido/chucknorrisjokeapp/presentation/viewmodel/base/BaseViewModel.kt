@file:Suppress("unused")

package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.base

import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * Chamado quando a view vai ser exibida.
     */
    @CallSuper
    open fun onViewIsAboutToBeShown() {
        Timber.d("View is about to be shown")
    }
}