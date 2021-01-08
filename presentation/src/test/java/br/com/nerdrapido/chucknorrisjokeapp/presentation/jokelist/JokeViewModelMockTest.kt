package br.com.nerdrapido.chucknorrisjokeapp.presentation.jokelist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeViewModelMock
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created By FELIPE GUSBERTI @ 07/01/2021
 */
class JokeViewModelMockTest {

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

    @ExperimentalCoroutinesApi
    private val dispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        MockitoAnnotations.openMocks(this)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test build mock JokeViewModelMockTest success`() {
        val viewModel = JokeViewModelMock.Builder()
            .addSuccess()
            .build()
        runBlocking {
            launch(dispatcher) {
                viewModel.randomJokeListLiveData.observeForever(randomJokeListObserver)
                viewModel.isLoading.observeForever(isLoadingObserver)
                viewModel.onNewJokeNeeded()

                Mockito.verify(
                    isLoadingObserver,
                    Mockito.timeout(1000)
                ).onChanged(true)
                Mockito.verify(
                    randomJokeListObserver,
                    Mockito.timeout(1000)
                ).onChanged(Mockito.anyList())
                Mockito.verify(
                    isLoadingObserver,
                    Mockito.timeout(1000)
                ).onChanged(false)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test build mock JokeViewModelMockTest api error`() {
        val viewModel = JokeViewModelMock.Builder()
            .addNetworkError(RuntimeException())
            .build()
        runBlocking {
            launch(dispatcher) {
                viewModel.isApiError.observeForever(isApiErrorObserver)
                viewModel.onNewJokeNeeded()
                Mockito.verify(
                    isApiErrorObserver,
                    Mockito.timeout(1000)
                ).onChanged(true)
            }
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `test build mock JokeViewModelMockTest generic error`() {
        val viewModel = JokeViewModelMock.Builder()
            .addGenericError(RuntimeException())
            .build()
        runBlocking {
            launch(dispatcher) {
                viewModel.isUnknownError.observeForever(isUnknownErrorObserver)
                viewModel.onNewJokeNeeded()
                Mockito.verify(
                    isUnknownErrorObserver,
                    Mockito.timeout(1000)
                ).onChanged(true)
            }
        }
    }

    @Test
    fun `test build mock JokeViewModelMockTest instantiation with delay`() {
        val viewModel = JokeViewModelMock.Builder()
            .addSuccess()
            .addDelay()
            .build()
    }

}