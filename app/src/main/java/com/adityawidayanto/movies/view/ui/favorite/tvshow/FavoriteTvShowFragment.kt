package com.adityawidayanto.movies.view.ui.favorite.tvshow

import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.FavoriteTvShowFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.tvshowlist.TvShowAdapter

class FavoriteTvShowFragment :
    BaseFragment<FavoriteTvShowFragmentBinding, FavoriteTvShowViewModel>() {

    private val adapter: TvShowAdapter by lazy {
        TvShowAdapter()
    }

    override fun getLayoutResourceId(): Int = R.layout.favorite_tv_show_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder().coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun initObservers() {
    }

    override fun initView() {
        binding.rvFavList.layoutManager = LinearLayoutManager(context)
        binding.rvFavList.adapter = adapter
    }

    override fun getViewModelClass(): Class<FavoriteTvShowViewModel> =
        FavoriteTvShowViewModel::class.java

}