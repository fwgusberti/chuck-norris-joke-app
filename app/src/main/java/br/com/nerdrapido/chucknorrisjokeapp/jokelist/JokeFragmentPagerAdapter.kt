package br.com.nerdrapido.chucknorrisjokeapp.jokelist

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import br.com.nerdrapido.chucknorrisjokeapp.jokelist.fragment.JokeDetailFragment
import br.com.nerdrapido.chucknorrisjokeapp.jokelist.fragment.JokeListErrorFragment
import br.com.nerdrapido.chucknorrisjokeapp.jokelist.fragment.JokeListFragment

/**
 * Created By FELIPE GUSBERTI @ 05/01/2021
 */
class JokeFragmentPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    companion object {
        const val NUM_PAGES = 3
    }

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> JokeListFragment()
            1 -> JokeDetailFragment()
            else -> JokeListErrorFragment()
        }
    }
}