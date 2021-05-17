package com.adityawidayanto.movies.view.ui.favorite.tvshow

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.core.utils.Constants
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.databinding.FavoriteTvShowFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.favorite.FavoriteFragmentDirections

class FavoriteTvShowFragment :
    BaseFragment<FavoriteTvShowFragmentBinding, FavoriteTvShowViewModel>() {

    private val adapter: TvShowPagingAdapter by lazy {
        TvShowPagingAdapter()
    }

    override fun getLayoutResourceId(): Int = R.layout.favorite_tv_show_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder().coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun initObservers() {
        vm.favTvShows.observe(viewLifecycleOwner, {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        })
    }

    override fun initView() {
        binding.rvFavTvShowList.layoutManager = LinearLayoutManager(context)
        binding.rvFavTvShowList.adapter = adapter

        adapter.onItemClick = { selected ->
            findNavController().navigate(
                FavoriteFragmentDirections.actionNavFavoriteToDetailFragment(
                    DetailBean(
                        selected.id,
                        selected.name,
                        selected.posterPath,
                        selected.overview,
                        selected.firstAirDate,
                        selected.backdropPath,
                        selected.popularity,
                        selected.voteAverage,
                        selected.voteCount
                    ),
                    Constants.POPULAR_TVSHOW
                )
            )
        }

    }

    override fun getViewModelClass(): Class<FavoriteTvShowViewModel> =
        FavoriteTvShowViewModel::class.java

}