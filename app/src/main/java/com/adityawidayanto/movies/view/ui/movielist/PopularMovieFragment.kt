package com.adityawidayanto.movies.view.ui.movielist

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.core.utils.Constants
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.databinding.PopularMovieFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.CustomFooterLoadStateAdapter
import com.adityawidayanto.movies.view.ui.favorite.movie.MoviePagingAdapter
import com.adityawidayanto.movies.view.ui.home.MainFragmentDirections

class PopularMovieFragment : BaseFragment<PopularMovieFragmentBinding, PopularMovieViewModel>() {

    private val adapter: MoviePagingAdapter by lazy {
        MoviePagingAdapter()
    }

    override fun initObservers() {
        vm.movies.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    override fun initView() {
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
        binding.rvMovieList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = CustomFooterLoadStateAdapter { adapter.retry() },
            footer = CustomFooterLoadStateAdapter { adapter.retry() }
        )
        adapter.onItemClick = { selected ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    DetailBean(
                        selected.id,
                        selected.title,
                        selected.posterPath,
                        selected.overview,
                        selected.releaseDate,
                        selected.backdropPath,
                        selected.popularity,
                        selected.voteAverage,
                        selected.voteCount
                    ),
                    Constants.POPULAR_MOVIE
                )
            )
        }
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