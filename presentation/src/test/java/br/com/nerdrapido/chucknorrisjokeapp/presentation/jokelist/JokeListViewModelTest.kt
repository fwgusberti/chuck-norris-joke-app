package br.com.nerdrapido.chucknorrisjokeapp.presentation.jokelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.usecase.RandomJokesUseCase
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeListViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper.JokePresentationMapper
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
@RunWith(JUnit4::class)
class JokeListViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var randomJokeListObserver: Observer<List<Joke>>

    @Mock
    lateinit var isApiErrorObserver: Observer<Boolean>

    @Mock
    lateinit var isUnknownErrorObserver: Observer<Boolean>

    @Mock
    lateinit var isLoadingObserver: Observer<Boolean>

    @Mock
    lateinit var jokesUseCase: RandomJokesUseCase

    lateinit var viewModel: JokeListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = JokeListViewModel(jokesUseCase, JokePresentationMapper())
    }

    @Test
    fun test_joke_list_load() = runBlocking {
        doReturn(EntityWrapper.Success(emptyList<JokeDomain>()))
            .`when`(jokesUseCase).execute(any(RandomJokesUseCase.Input::class.java))

        viewModel.randomJokeList.observeForever(randomJokeListObserver)
        viewModel.isLoading.observeForever(isLoadingObserver)
        viewModel.onViewIsAboutToBeShown()

        verify(
            isLoadingObserver,
            timeout(1000)
        ).onChanged(true)
        verify(
            randomJokeListObserver,
            timeout(1000)
        ).onChanged(anyList())
        verify(
            isLoadingObserver,
            timeout(1000)
        ).onChanged(false)
    }

    @Test
    fun test_joke_list_load_network_exception() = runBlocking {
        doReturn(EntityWrapper.NetworkError(IOException("Test")))
            .`when`(jokesUseCase).execute(any(RandomJokesUseCase.Input::class.java))
        viewModel.isApiError.observeForever(isApiErrorObserver)
        viewModel.onViewIsAboutToBeShown()
        verify(
            isApiErrorObserver,
            timeout(1000)
        ).onChanged(true)
    }

    @Test
    fun test_joke_list_load_unknown_exception() = runBlocking {
        doReturn(EntityWrapper.GenericError(RuntimeException("Test")))
            .`when`(jokesUseCase).execute(any(RandomJokesUseCase.Input::class.java))
        viewModel.isUnknownError.observeForever(isUnknownErrorObserver)
        viewModel.onViewIsAboutToBeShown()
        verify(
            isUnknownErrorObserver,
            timeout(1000)
        ).onChanged(true)
    }
}