package br.com.nerdrapido.chucknorrisjokeapp.joke

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeLeft
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.common.EspressoHelper
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeViewModel
import br.com.nerdrapido.chucknorrisjokeapp.presentation.viewmodel.jokelist.JokeViewModelMock
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.MockitoAnnotations
import java.io.IOException

/**
 * Created By FELIPE GUSBERTI @ 07/01/2021
 */
@RunWith(AndroidJUnit4::class)
class JokeActivityTest : KoinTest {


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun test_single_jokes_navigation() {
        loadKoinModules(
            module {
                factory<JokeViewModel>(override = true) {
                    JokeViewModelMock.Builder()
                        .addSuccess()
                        .build()
                }
            }
        )
        ActivityScenario.launch(JokeActivity::class.java)
        EspressoHelper.waitViewAppear(onView(withId(R.id.jokeListActivity)))
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
    }

    @Test
    fun test_single_jokes_load() {
        loadKoinModules(
            module {
                factory<JokeViewModel>(override = true) {
                    JokeViewModelMock.Builder()
                        .addSuccess()
                        .addDelay(1000)
                        .build()
                }
            }
        )
        ActivityScenario.launch(JokeActivity::class.java)
        EspressoHelper.waitViewAppear(onView(withId(R.id.jokeListActivity)))
        EspressoHelper.waitViewAppear(onView(withId(R.id.jokePb)))
        onView(withId(R.id.jokeFragmentContainerVp)).perform(swipeLeft())
    }

    @Test
    fun test_api_error() {
        loadKoinModules(
            module {
                factory<JokeViewModel>(override = true) {
                    JokeViewModelMock.Builder()
                        .addNetworkError(IOException())
                        .build()
                }
            }
        )
        ActivityScenario.launch(JokeActivity::class.java)
        EspressoHelper.waitViewAppear(onView(withId(R.id.jokeListActivity)))
        EspressoHelper.waitViewAppear(onView(withText("Sorry. Error loading the joke. Check your connection.")))
    }

    @Test
    fun test_generic_error() {
        loadKoinModules(
            module {
                factory<JokeViewModel>(override = true) {
                    JokeViewModelMock.Builder()
                        .addGenericError(IOException())
                        .build()
                }
            }
        )
        ActivityScenario.launch(JokeActivity::class.java)
        EspressoHelper.waitViewAppear(onView(withId(R.id.jokeListActivity)))
        EspressoHelper.waitViewAppear(onView(withText("Sorry. Error loading the joke.")))
    }

    @Test
    fun test_try_again_error() {
        loadKoinModules(
            module {
                factory<JokeViewModel>(override = true) {
                    JokeViewModelMock.Builder()
                        .addGenericError(IOException())
                        .addDelay(500L)
                        .build()
                }
            }
        )
        ActivityScenario.launch(JokeActivity::class.java)
        EspressoHelper.waitViewAppear(onView(withId(R.id.jokeListActivity)))
        EspressoHelper.waitViewAppear(onView(withText("Sorry. Error loading the joke.")))
        onView(withText("Try Again")).perform(click())
        EspressoHelper.waitViewAppear(onView(withText("Trying again!")))
    }
}
