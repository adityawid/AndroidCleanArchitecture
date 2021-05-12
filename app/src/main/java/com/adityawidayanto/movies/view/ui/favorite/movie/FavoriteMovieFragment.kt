package com.adityawidayanto.movies.view.ui.favorite.movie

import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.FavoriteMovieFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.movielist.MovieAdapter

class FavoriteMovieFragment : BaseFragment<FavoriteMovieFragmentBinding, FavoriteMovieViewModel>() {

    private val adapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun getLayoutResourceId(): Int = R.layout.favorite_movie_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder().coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun initObservers() {
    }

    override fun initView() {
        binding.rvFavMovieList.layoutManager = LinearLayoutManager(context)
        binding.rvFavMovieList.adapter = adapter
    }

    override fun getViewModelClass(): Class<FavoriteMovieViewModel> =
        FavoriteMovieViewModel::class.java

}