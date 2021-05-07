package com.adityawidayanto.movies.view.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.core.utils.test.EspressoIdlingResource
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.databinding.PopularMovieFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.home.MainFragmentDirections

class PopularMovieFragment : BaseFragment<PopularMovieFragmentBinding, PopularMovieViewModel>() {

    private val adapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EspressoIdlingResource.increment()
        binding.movieProgressBar.visibility = View.VISIBLE
        vm.getPopularMovie()
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
                    )
                )
            )
        }
    }

    override fun initObservers() {
        vm.movieList.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.movieProgressBar.visibility = View.GONE
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
                adapter.setData(it)
                adapter.notifyDataSetChanged()
            }

        })
    }

    override fun initView() {
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
        binding.rvMovieList.adapter = adapter
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