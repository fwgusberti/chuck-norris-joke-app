package br.com.nerdrapido.chucknorrisjokeapp.joke

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.nerdrapido.chucknorrisjokeapp.R
import br.com.nerdrapido.chucknorrisjokeapp.joke.fragment.SingleJokeFragment
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class JokeFragmentPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    companion object {
        const val NUM_PAGES = 1000
    }

    private val jokeColors = listOf(
        R.color.colorJoke1,
        R.color.colorJoke2,
        R.color.colorJoke3,
        R.color.colorJoke4,
        R.color.colorJoke5
    )

    private var colorIndex = 0

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        Timber.d("SingleJokeFragment newInstance createFragment $position")
        return SingleJokeFragment.newInstance(position, getBackgroundColor())
    }

    private fun getBackgroundColor(): Int {
        val color = jokeColors[colorIndex]
        colorIndex++
        if (colorIndex == jokeColors.size) {
            colorIndex = 0
        }
        return color
    }
}
