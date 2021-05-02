package com.adityawidayanto.movies.view.ui.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.core.CoreApp
import com.adityawidayanto.core.ui.BaseFragment
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.R
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.databinding.PopularMovieFragmentBinding
import com.adityawidayanto.movies.di.DaggerMovieComponent
import com.adityawidayanto.movies.view.ui.home.MainFragmentDirections

class PopularMovieFragment : BaseFragment<PopularMovieFragmentBinding, PopularMovieViewModel>() {

    private var movieData = arrayListOf<Movie>()
    private val adapter: MovieAdapter by lazy {
        MovieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.getPopularMovie()
        adapter.onItemClick = { selected ->
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToDetailFragment(
                    DetailBean(selected.id, selected.title, selected.posterPath, selected.overview)
                )
            )
            Toast.makeText(context, "judul " + selected.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun initObservers() {
        vm.movieList.observe(viewLifecycleOwner, {
            println("test Adit movielist : $it")
            adapter.setData(it)
            adapter.notifyDataSetChanged()
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