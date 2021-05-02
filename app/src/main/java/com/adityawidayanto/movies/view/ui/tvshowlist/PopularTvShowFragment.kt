package com.adityawidayanto.movies.view.ui.tvshowlist

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.PopularTvShowFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent

class PopularTvShowFragment : BaseFragment<PopularTvShowFragmentBinding, PopularTvShowViewModel>() {
    private val adapter: TvShowAdapter by lazy {
        TvShowAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getPopularTvShow()
    }

    override fun getLayoutResourceId(): Int = R.layout.popular_tv_show_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder()
            .coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun initObservers() {
        vm.tvShowList.observe(viewLifecycleOwner, {
            println("test Adit tvShowlist : $it")
            adapter.setData(it)
            adapter.notifyDataSetChanged()
        })
    }

    override fun initView() {
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = adapter
    }

    override fun getViewModelClass(): Class<PopularTvShowViewModel> =
        PopularTvShowViewModel::class.java

}