package br.com.nerdrapido.chucknorrisjokeapp.jokelist

import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.base.BaseActivity
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class JokeListActivity : BaseActivity<JokeListViewModel>() {

    override val layoutId: Int = R.layout.activity_joke_list
    override val viewModel: JokeListViewModel by viewModel()

    override fun registerObservers() {

    }
}