package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.splash

import androidx.lifecycle.MutableLiveData
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.base.BaseViewModel
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class SplashScreenViewModel : BaseViewModel() {

    val splashEnded = MutableLiveData<Boolean>()

    override fun onViewIsAboutToBeShown() {
        super.onViewIsAboutToBeShown()
        Executors.newSingleThreadScheduledExecutor().schedule({
            splashEnded.postValue(true)
        }, 500, TimeUnit.MILLISECONDS)
    }
}