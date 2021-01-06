package br.com.nerdrapido.chucknorrisjokeapp.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.base.BaseViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
abstract class BaseActivity<T : BaseViewModel> : AppCompatActivity() {

    /**
     * O layoutId a ser utilizado nesta atividade
     */
    protected abstract val layoutId: Int

    /**
     * O viewModel [T] desta view.
     */
    protected abstract val viewModel: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateCall()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        onCreateCall()
    }

    /**
     * Função com o que deve ser chamado no [onCreate].
     * Ambas assinaturas do on create devem ser sobrescritas pois se não podem ocrrer problemas em
     * reaberturas do aplicativo, desta forma, este método foi criado para evitar repetir código.
     *
     * @Suppress("DEPRECATION"): Resources.getColor(int, Theme) does not support min android version
     *
     */
    @SuppressLint("SourceLockedOrientationActivity")
    protected open fun onCreateCall() {
        @Suppress("UNCHECKED_CAST")
        setContentView(layoutId)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        registerObservers()
    }

    override fun onResume() {
        super.onResume()
        viewModel.onViewIsAboutToBeShown()
    }

    @CallSuper
    open fun registerObservers() {
        viewModel.isLoading.observe(this, getIsLoadingObserver())
    }

    abstract fun getIsLoadingObserver(): Observer<Boolean>

}