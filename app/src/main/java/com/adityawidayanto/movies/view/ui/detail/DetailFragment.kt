package com.adityawidayanto.movies.view.ui.detail

import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.DetailFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.bumptech.glide.Glide

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    private val args: DetailFragmentArgs by navArgs()

    override fun getLayoutResourceId(): Int = R.layout.detail_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder().coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun initObservers() {
        vm.detail.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val posterURL = "https://image.tmdb.org/t/p/w500" + it.poster
                val backDropUrl = "https://image.tmdb.org/t/p/w500" + it.backDrop
//
//                binding.titleDetail.text = it.title
//                binding.overview.text = it.overview
//                binding.popularity.text = it.popularity.toString()
                Glide.with(binding.posterTopBar.context)
                    .load(backDropUrl)
                    .into(binding.posterTopBar)

                Glide.with(binding.subPoster.context)
                    .load(posterURL)
                    .into(binding.subPoster)
            }
        })
    }

    override fun initView() {
        vm.initDetail(args.detail)
        binding.viewModel = vm
    }

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

}