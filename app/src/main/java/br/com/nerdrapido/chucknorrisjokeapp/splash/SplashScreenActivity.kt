package br.com.nerdrapido.chucknorrisjokeapp.splash

import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.base.BaseActivity
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.splash.SplashScreenViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class SplashScreenActivity : BaseActivity<SplashScreenViewModel>() {

    override val layoutId = R.layout.activity_splash_screen

    override val viewModel: SplashScreenViewModel by viewModel()

}