package br.com.nerdrapido.chucknorrisjokeapp.jokelist

import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.base.BaseActivity
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeListViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke
import kotlinx.android.synthetic.main.activity_joke_list.*
import org.koin.android.viewmodel.ext.android.viewModel


/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class JokeListActivity : BaseActivity<JokeListViewModel>() {

    override val layoutId: Int = R.layout.activity_joke_list

    override val viewModel: JokeListViewModel by viewModel()

    override fun registerObservers() {
        super.registerObservers()
        viewModel.randomJokeList.observe(this, getJokeListObserver())
    }

    private fun getJokeListObserver(): Observer<List<Joke>> {
        return Observer {

        }
    }

    override fun getIsLoadingObserver(): Observer<Boolean> {
        return Observer {
            if (it) {
                jokePb.show()
            } else {
                jokePb.hide()
            }
        }
    }
}