package com.adityawidayanto.movies.view.ui.tvshowlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.adityawidayanto.movies.R

class PopularTvShowFragment : Fragment() {

    companion object {
        fun newInstance() = PopularTvShowFragment()
    }

    private lateinit var viewModel: PopularTvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.popular_tv_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PopularTvShowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}