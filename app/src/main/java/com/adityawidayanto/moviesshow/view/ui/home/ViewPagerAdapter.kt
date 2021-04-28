package com.adityawidayanto.moviesshow.view.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adityawidayanto.moviesshow.view.ui.movies.MoviesFragment
import com.adityawidayanto.moviesshow.view.ui.tvshow.TvShowFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MoviesFragment()
        } else {
            TvShowFragment()
        }
    }

}