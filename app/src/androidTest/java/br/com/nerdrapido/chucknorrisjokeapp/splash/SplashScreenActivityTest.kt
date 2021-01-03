package br.com.nerdrapido.chucknorrisjokeapp.splash

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.nerdrapido.chucknorrisjokeapp.R
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created By FELIPE GUSBERTI @ 03/01/2021
 */
@RunWith(AndroidJUnit4::class)
class SplashScreenActivityTest {

    @Test
    fun test_splash_screen_launch() {
        ActivityScenario.launch(SplashScreenActivity::class.java)
        Espresso.onView(withId(R.id.nerdRapidoIv))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}