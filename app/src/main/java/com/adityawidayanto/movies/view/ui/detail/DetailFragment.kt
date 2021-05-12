package com.adityawidayanto.movies.view.ui.detail

import androidx.navigation.fragment.navArgs
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.core.utils.Constants
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.databinding.DetailFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailFragment : BaseFragment<DetailFragmentBinding, DetailViewModel>() {
    private val args: DetailFragmentArgs by navArgs()

    override fun getLayoutResourceId(): Int = R.layout.detail_fragment

    override fun initDaggerComponent() {
        DaggerMovieComponent.builder().coreComponent(CoreApp.coreComponent(requireContext()))
            .build()
            .inject(this)
    }

    override fun initObservers() {
        vm.detail.observe(viewLifecycleOwner, {
            if (it != null) {
                val posterURL = "https://image.tmdb.org/t/p/w500" + it.poster
                val backDropUrl = "https://image.tmdb.org/t/p/w500" + it.backDrop
                Glide.with(binding.posterTopBar.context)
                    .load(backDropUrl)
                    .into(binding.posterTopBar)

                Glide.with(binding.subPoster.context)
                    .load(posterURL)
                    .into(binding.subPoster)


                var isChecked = false
                CoroutineScope(Dispatchers.IO).launch {
                    val count: Int = if (args.source == Constants.POPULAR_MOVIE) {
                        vm.checkIdMovie(
                            Movie(
                                it.id,
                                it.overview,
                                it.poster,
                                it.releaseDate,
                                it.title,
                                it.backDrop,
                                it.popularity,
                                it.voteAverage,
                                it.voteCount
                            )
                        )
                    } else {
                        vm.checkIdTvShow(
                            TvShow(
                                it.releaseDate,
                                it.id,
                                it.title,
                                it.overview,
                                it.poster,
                                it.backDrop,
                                it.popularity,
                                it.voteAverage,
                                it.voteCount
                            )
                        )
                    }
                    withContext(Dispatchers.Main) {
                        if (count > 0) {
                            binding.btnFavorite.isChecked = true
                            isChecked = true
                        } else {
                            binding.btnFavorite.isChecked = false
                            isChecked = false
                        }
                    }
                }

                binding.btnFavorite.setOnClickListener {
                    isChecked = !isChecked
                    binding.btnFavorite.isChecked = isChecked
                    if (isChecked) {
                        if (args.source == Constants.POPULAR_MOVIE) {
                            vm.addMovieFavorite(
                                Movie(
                                    args.detail.id,
                                    args.detail.overview,
                                    args.detail.poster,
                                    args.detail.releaseDate,
                                    args.detail.title,
                                    args.detail.backDrop,
                                    args.detail.popularity,
                                    args.detail.voteAverage,
                                    args.detail.voteCount
                                )
                            )
                        } else {
                            vm.addTvShowFavorite(
                                TvShow(
                                    args.detail.releaseDate,
                                    args.detail.id,
                                    args.detail.title,
                                    args.detail.overview,
                                    args.detail.poster,
                                    args.detail.backDrop,
                                    args.detail.popularity,
                                    args.detail.voteAverage,
                                    args.detail.voteCount
                                )
                            )
                        }

                    } else {
                        if (args.source == Constants.POPULAR_MOVIE) {
                            vm.removeMovieFavorite(
                                Movie(
                                    args.detail.id,
                                    args.detail.overview,
                                    args.detail.poster,
                                    args.detail.releaseDate,
                                    args.detail.title,
                                    args.detail.backDrop,
                                    args.detail.popularity,
                                    args.detail.voteAverage,
                                    args.detail.voteCount
                                )
                            )
                        } else {
                            vm.removeTvShowFavorite(
                                TvShow(
                                    args.detail.releaseDate,
                                    args.detail.id,
                                    args.detail.title,
                                    args.detail.overview,
                                    args.detail.poster,
                                    args.detail.backDrop,
                                    args.detail.popularity,
                                    args.detail.voteAverage,
                                    args.detail.voteCount
                                )
                            )
                        }

                    }
                }

            }
        })
    }

    override fun initView() {
        vm.initDetail(args.detail)
        binding.viewModel = vm

    }

    override fun getViewModelClass(): Class<DetailViewModel> = DetailViewModel::class.java

}