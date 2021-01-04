package br.com.nerdrapido.chucknorrisjokeapp.presentation.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.splash.SplashScreenViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
@RunWith(AndroidJUnit4::class)
class SplashScreenViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var splashEndedObserver: Observer<Boolean>

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test_SplashScreenViewModel_splashEnded() {
        val splashScreenViewModel = SplashScreenViewModel()

        runBlocking {
            splashScreenViewModel.splashEnded.observeForever(splashEndedObserver)
            splashScreenViewModel.onViewIsAboutToBeShown()
            Mockito.verify(
                splashEndedObserver,
                Mockito.timeout(1000)
            ).onChanged(true)
        }
    }
}