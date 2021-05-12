package com.adityawidayanto.movies.view.ui.favorite

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.adityawidayanto.movies.view.ui.favorite.movie.FavoriteMovieFragment
import com.adityawidayanto.movies.view.ui.favorite.tvshow.FavoriteTvShowFragment

class FavPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            FavoriteMovieFragment()
        } else {
            FavoriteTvShowFragment()
        }
    }
}