package com.adityawidayanto.moviesshow.view.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.moviesshow.R
import com.adityawidayanto.moviesshow.databinding.MoviesFragmentBinding

class MoviesFragment : Fragment() {
    private val viewModel: MoviesViewModel by activityViewModels()

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
        initViewModels()
        initEvent()
    }

    private lateinit var adapter: MovieAdapter
    private fun initEvent() {
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
        adapter = MovieAdapter()
        binding.rvMovieList.adapter = adapter
//        displayPopularMovies()
    }

    private fun initViewModels() {
        viewModel.movieList.observe(viewLifecycleOwner) {
            if (it != null) {

            }
        }
    }

}