package br.com.nerdrapido.chucknorrisjokeapp.splash

import android.content.Intent
import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.base.BaseActivity
import br.com.nerdrapido.chucknorrisjokeapp.jokelist.JokeListActivity
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.splash.SplashScreenViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class SplashScreenActivity : BaseActivity<SplashScreenViewModel>() {

    override val layoutId = R.layout.activity_splash_screen

    override val viewModel: SplashScreenViewModel by viewModel()

    override fun registerObservers() {
        viewModel.splashEnded.observe(this, Observer {
            val newIntent = Intent(this, JokeListActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(newIntent)
        })
    }

}