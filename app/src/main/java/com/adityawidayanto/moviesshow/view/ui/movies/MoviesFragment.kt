package com.adityawidayanto.moviesshow.view.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adityawidayanto.moviesshow.R
import com.adityawidayanto.moviesshow.databinding.MoviesFragmentBinding
import com.adityawidayanto.moviesshow.di.AppModule
import com.adityawidayanto.moviesshow.di.DaggerAppComponent
import javax.inject.Inject

class MoviesFragment : Fragment() {
    @Inject
    lateinit var factory: MovieViewModelFactory
    private lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private lateinit var binding: MoviesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.movies_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        DaggerAppComponent.builder().appModule(AppModule())
            .build().movieComponent().create().inject(this)
//        DaggerMovieComponent.builder()
//            .movieModule(MovieModule())
//            .build()
//            .inject(this)
        initViewModels()
        initEvent()
    }

    private lateinit var adapter: MovieAdapter
    private fun initEvent() {
//        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
//        adapter = MovieAdapter()
//        binding.rvMovieList.adapter = adapter
        viewModel.getMovies()
//        displayPopularMovies()
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, factory)
            .get(MoviesViewModel::class.java)
//        viewModel.movieList.observe(viewLifecycleOwner) {
//            if (it != null && it.isNotEmpty()) {
//                println("test Adit "+it.size)
//                println("test Adit "+it[0].title)
//            }
//        }
    }

}