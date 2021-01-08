package br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist

import androidx.lifecycle.MutableLiveData
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.usecase.RandomJokesUseCase
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.annotation.OpenForTesting
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.base.BaseViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper.JokePresentationMapper
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
const val NUMBER_OF_JOKES_LOADED_AT_A_TIME = 1

@OpenForTesting
class JokeViewModel(
    private val jokesUseCase: RandomJokesUseCase,
    private val jokePresentationMapper: JokePresentationMapper
) : BaseViewModel() {

    val randomJokeListLiveData = MutableLiveData<List<Joke>>()

    private val randomJokeList = mutableListOf<Joke>()

    fun onNewJokeNeeded() {
        loadOneRandomJoke {
            randomJokeList.add(it)
            randomJokeListLiveData.postValue(randomJokeList)
        }
    }

    private fun <T : Any> loadOneRandomJoke(call: (joke: Joke) -> T) {
        GlobalScope.launch(Dispatchers.Main) {
            isLoading.postValue(true)
            when (val jokesWrapper =
                jokesUseCase.execute(RandomJokesUseCase.Input(NUMBER_OF_JOKES_LOADED_AT_A_TIME))) {
                is EntityWrapper.Success<List<JokeDomain>> -> {
                    val jokes =
                        jokePresentationMapper.mapFromDomainToPresentationList(jokesWrapper.value)
                        isApiError.postValue(false)
                    Timber.d(jokes.toString())
                    if (jokes.isNotEmpty()) {
                        call.invoke(jokes[0])
                    } else {
                        isApiError.postValue(true)
                    }
                }
                is EntityWrapper.NetworkError -> {
                    Timber.e(jokesWrapper.error)
                    isApiError.postValue(true)
                }
                else -> {
                    jokesWrapper as EntityWrapper.GenericError
                    Timber.e(jokesWrapper.error)
                    isUnknownError.postValue(true)
                }
            }
            isLoading.postValue(false)
        }
    }
}
