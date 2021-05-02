package com.adityawidayanto.movies.view.ui.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adityawidayanto.movies.view.ui.movielist.PopularMovieFragment
import com.adityawidayanto.movies.view.ui.tvshowlist.PopularTvShowFragment

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            PopularMovieFragment()
        } else {
            PopularTvShowFragment()
        }
    }

}