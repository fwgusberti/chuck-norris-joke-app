@file:Suppress("unused")

package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.base

import androidx.annotation.CallSuper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * True when useCase returns API network error.
     */
    val isApiError = MutableLiveData<Boolean>()

    /**
     * True when useCase returns an unknown error.
     */
    val isUnknownError = MutableLiveData<Boolean>()

    /**
     * True when is loading data.
     */
    val isLoading = MutableLiveData<Boolean>()

    /**
     * Called when view is about to be shown.
     */
    @CallSuper
    open fun onViewIsAboutToBeShown() {
        Timber.d("View is about to be shown")
    }
}