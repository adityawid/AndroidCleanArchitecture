package com.adityawidayanto.movies.view.ui.tvshowlist

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.core.utils.test.EspressoIdlingResource
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.databinding.PopularTvShowFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.home.MainFragmentDirections

class PopularTvShowFragment : BaseFragment<PopularTvShowFragmentBinding, PopularTvShowViewModel>() {
    private val adapter: TvShowAdapter by lazy {
        TvShowAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        EspressoIdlingResource.increment()
        binding.progressBar.visibility = View.VISIBLE
        vm.getPopularTvShow()
        adapter.onItemClick = { selected ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
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
                    )
                )
            )
        }
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
            if (it != null) {
                binding.progressBar.visibility = View.GONE
                if (!EspressoIdlingResource.idlingResource.isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
                adapter.setData(it)
                adapter.notifyDataSetChanged()
            }
        })
    }

    override fun initView() {
        binding.rvList.layoutManager = LinearLayoutManager(context)
        binding.rvList.adapter = adapter
    }

    override fun getViewModelClass(): Class<PopularTvShowViewModel> =
        PopularTvShowViewModel::class.java

}