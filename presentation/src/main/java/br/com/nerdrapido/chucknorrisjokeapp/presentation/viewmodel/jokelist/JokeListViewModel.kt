package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist

import androidx.lifecycle.MutableLiveData
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.usecase.RandomJokesUseCase
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.base.BaseViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper.JokePresentationMapper
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
class JokeListViewModel(
    private val jokesUseCase: RandomJokesUseCase,
    private val jokePresentationMapper: JokePresentationMapper
) : BaseViewModel() {

    val randomJokeList = MutableLiveData<List<Joke>>()

    override fun onViewIsAboutToBeShown() {
        super.onViewIsAboutToBeShown()
        loadRandomJokes()
    }

    private fun loadRandomJokes() {
        GlobalScope.launch {
            isLoading.postValue(true)
            when (val jokes = jokesUseCase.execute(RandomJokesUseCase.Input())) {
                is EntityWrapper.Success<List<JokeDomain>> -> {
                    val joke = jokePresentationMapper.mapFromDomainToPresentationList(jokes.value)
                    randomJokeList.postValue(joke)
                }
                is EntityWrapper.NetworkError -> {
                    Timber.e(jokes.error)
                    isApiError.postValue(true)
                }
                else -> {
                    jokes as EntityWrapper.GenericError
                    Timber.e(jokes.error)
                    isUnknownError.postValue(true)
                }
            }
            isLoading.postValue(false)
        }
    }
}
