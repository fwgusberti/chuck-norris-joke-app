package br.com.nerdrapido.chucknorrisjokeapp.presentation.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.splash.SplashScreenViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
@RunWith(JUnit4::class)
class SplashScreenViewModelTest  {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    lateinit var splashEndedObserver: Observer<Boolean>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_SplashScreenViewModel_splashEnded() = runBlocking {
        val splashScreenViewModel = SplashScreenViewModel()
        splashScreenViewModel.splashEnded.observeForever(splashEndedObserver)
        splashScreenViewModel.onViewIsAboutToBeShown()
        Mockito.verify(
            splashEndedObserver,
            Mockito.timeout(1000)
        ).onChanged(true)
    }

}