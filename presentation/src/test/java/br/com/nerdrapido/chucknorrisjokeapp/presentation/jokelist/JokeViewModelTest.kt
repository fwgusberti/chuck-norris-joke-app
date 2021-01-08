package br.com.nerdrapido.chucknorrisjokeapp.presentation.jokelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.EntityWrapper
import br.com.nerdrapido.chucknorrisjokeapp.domain.model.JokeDomain
import br.com.nerdrapido.chucknorrisjokeapp.domain.usecase.RandomJokesUseCase
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.mapper.JokePresentationMapper
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.model.Joke
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.io.IOException
import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
@RunWith(JUnit4::class)
class JokeViewModelTest {

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

    private val id: String = "12345asdasd"
    private val categories: List<String> = emptyList()
    private val createdAtTimestamp = 1578232581843L
    private val iconUrl: String = "oidhgfoirdsg"
    private val updatedAtTimestamp = 1578318981843L
    private val url: String = "lzsidjflçdjf"
    private val value: String = "lzsidjflçdjf asdasd asdasd"

    private val anyJoke = JokeDomain(
        id,
        categories,
        Date(createdAtTimestamp),
        iconUrl,
        Date(updatedAtTimestamp),
        url,
        value
    )

    private lateinit var viewModel: JokeViewModel

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
        viewModel = JokeViewModel(jokesUseCase, JokePresentationMapper())
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test onNewJokeNeeded returning one joke`() {
        runBlocking {
            launch(dispatcher) {
                doReturn(EntityWrapper.Success(listOf(anyJoke)))
                    .`when`(jokesUseCase).execute(any(RandomJokesUseCase.Input::class.java))

                viewModel.randomJokeListLiveData.observeForever(randomJokeListObserver)
                viewModel.isLoading.observeForever(isLoadingObserver)
                viewModel.onNewJokeNeeded()

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
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test onNewJokeNeeded returning no joke`() {
        runBlocking {
            launch(dispatcher) {
                doReturn(EntityWrapper.Success(emptyList<JokeDomain>()))
                    .`when`(jokesUseCase).execute(any(RandomJokesUseCase.Input::class.java))

                viewModel.isApiError.observeForever(isApiErrorObserver)
                viewModel.isLoading.observeForever(isLoadingObserver)
                viewModel.onNewJokeNeeded()

                verify(
                    isLoadingObserver,
                    timeout(1000)
                ).onChanged(true)
                verify(
                    isApiErrorObserver,
                    timeout(1000)
                ).onChanged(true)
                verify(
                    isLoadingObserver,
                    timeout(1000)
                ).onChanged(false)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_joke_list_load_network_exception() {
        runBlocking {
            launch(dispatcher) {
                doReturn(EntityWrapper.NetworkError(IOException("Test")))
                    .`when`(jokesUseCase).execute(any(RandomJokesUseCase.Input::class.java))
                viewModel.isApiError.observeForever(isApiErrorObserver)
                viewModel.onNewJokeNeeded()
                verify(
                    isApiErrorObserver,
                    timeout(1000)
                ).onChanged(true)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun test_joke_list_load_unknown_exception() {
        runBlocking {
            launch(dispatcher) {
                doReturn(EntityWrapper.GenericError(RuntimeException("Test")))
                    .`when`(jokesUseCase).execute(any(RandomJokesUseCase.Input::class.java))
                viewModel.isUnknownError.observeForever(isUnknownErrorObserver)
                viewModel.onNewJokeNeeded()
                verify(
                    isUnknownErrorObserver,
                    timeout(1000)
                ).onChanged(true)
            }
        }
    }
}