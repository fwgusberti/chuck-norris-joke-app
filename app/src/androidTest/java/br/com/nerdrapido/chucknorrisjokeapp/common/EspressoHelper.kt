package br.com.nerdrapido.chucknorrisjokeapp.common

import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
object EspressoHelper {

    /**
     * Helper that waits for a view appear.
     *
     * @param viewInteraction The view interaction.
     * @param timeout The timeout in millis. Default is 5000 (5 seconds).
     */
    fun waitViewAppear(viewInteraction: ViewInteraction, timeout: Int = 5000) {
        var i = timeout / 10
        var throwable: Throwable? = null
        while (i > 0) {
            try {
                viewInteraction.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                break
            } catch (t: Throwable) {
                i--
                throwable = t
                Thread.sleep(10)
            }
        }
        if (i == 0) {
            throw throwable!!
        }
    }
}