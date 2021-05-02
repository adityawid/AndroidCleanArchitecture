package com.adityawidayanto.movies.view.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.PopularMovieFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent

class PopularMovieFragment : BaseFragment<PopularMovieFragmentBinding, PopularMovieViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
        vm.getPopularMovie()
    }

    private fun registerObservers() {
        vm.movieList.observe(viewLifecycleOwner, Observer { println("test Adit movielist : $it") })
    }

    override fun getLayoutResourceId(): Int = R.layout.popular_movie_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder().coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun getViewModelClass(): Class<PopularMovieViewModel> =
        PopularMovieViewModel::class.java

}