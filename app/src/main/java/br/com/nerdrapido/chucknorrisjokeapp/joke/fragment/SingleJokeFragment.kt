package br.com.nerdrapido.chucknorrisjokeapp.joke.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke
import kotlinx.android.synthetic.main.fragment_single_joke.*
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class SingleJokeFragment : Fragment() {

    companion object {
        fun newInstance(index: Int, color: Int): SingleJokeFragment {
            val fragment = SingleJokeFragment()
            fragment.index = index
            fragment.backgroundColor = color
            fragment.isInstantiationOk = true
            Timber.d("SingleJokeFragment newInstance")
            return fragment
        }
    }

    private val viewModel: JokeViewModel by activityViewModels()

    private var backgroundColor: Int = -1

    private var index: Int = -1

    private var isInstantiationOk = false

    private val observer = getObserver()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        checkInstantiation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_single_joke, container, false)
        viewModel.onNewJokeNeeded()
        viewModel.randomJokeListLiveData.observe(viewLifecycleOwner, observer)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backgroundColor.let {
            backgroundView.setBackgroundColor(
                resources.getColor(it)
            )
        }
    }

    private fun getObserver(): Observer<List<Joke>> = Observer {
        Timber.d("New Joke coming")
        if (it.size > index) {
            setupJokeInView(it[index])
            viewModel.randomJokeListLiveData.removeObserver(observer)
        } else {
            viewModel.onNewJokeNeeded()
        }
    }

    private fun setupJokeInView(joke: Joke) {
        Timber.d("setupJokeInView ${joke.id}")
        jokeValueTv.text = joke.value
    }

    /**
     * If the fragment was instantiated incorrectly an [IllegalAccessException] will be thrown.
     *
     * @throws IllegalAccessException
     */
    private fun checkInstantiation() {
        if (!isInstantiationOk) {
            throw IllegalAccessException("You must instantiate SingleJokeFragment with method SingleJokeFragment.newInstance")
        }
    }
}