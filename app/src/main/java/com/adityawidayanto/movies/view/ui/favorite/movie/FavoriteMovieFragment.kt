package com.adityawidayanto.movies.view.ui.favorite.movie

import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.core.utils.Constants
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.databinding.FavoriteMovieFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.favorite.FavoriteFragmentDirections
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteMovieFragment : BaseFragment<FavoriteMovieFragmentBinding, FavoriteMovieViewModel>() {

    private val adapter: MoviePagingAdapter by lazy {
        MoviePagingAdapter()
    }

    override fun getLayoutResourceId(): Int = R.layout.favorite_movie_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder().coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun initObservers() {
//        vm.favMovies.observe(viewLifecycleOwner, {
//            adapter.submitData(viewLifecycleOwner.lifecycle, it)
//        })
        lifecycleScope.launch {
            vm.favMovies.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    override fun initView() {
        binding.rvFavMovieList.layoutManager = LinearLayoutManager(context)
        binding.rvFavMovieList.adapter = adapter
        adapter.onItemClick = { selected ->
            findNavController().navigate(
                FavoriteFragmentDirections.actionNavFavoriteToDetailFragment(
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

    override fun getViewModelClass(): Class<FavoriteMovieViewModel> =
        FavoriteMovieViewModel::class.java

}