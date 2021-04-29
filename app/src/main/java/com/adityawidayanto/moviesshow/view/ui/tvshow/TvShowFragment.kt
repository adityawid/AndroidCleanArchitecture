package com.adityawidayanto.moviesshow.view.ui.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityawidayanto.moviesshow.R
import com.adityawidayanto.moviesshow.databinding.TvShowFragmentBinding
import com.adityawidayanto.moviesshow.di.AppModule
import com.adityawidayanto.moviesshow.di.DaggerAppComponent
import com.adityawidayanto.moviesshow.view.ui.home.HomeFragmentDirections
import javax.inject.Inject

class TvShowFragment : Fragment() {

    @Inject
    lateinit var factory: TvShowViewModelFactory
    private lateinit var viewModel: TvShowViewModel
    private lateinit var binding: TvShowFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.tv_show_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        DaggerAppComponent.builder().appModule(AppModule())
            .build().tvShowComponent().create().inject(this)
        initViewModels()
        initEvent()
    }

    private lateinit var adapter: TvShowAdapter
    private fun initEvent() {
        binding.rvMovieList.layoutManager = LinearLayoutManager(context)
        adapter = TvShowAdapter()
        binding.rvMovieList.adapter = adapter

        viewModel.getMovies()
        adapter.onItemClick = { selected ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
            Toast.makeText(context, "judul " + selected.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this, factory)
            .get(TvShowViewModel::class.java)
        viewModel.tvShowList.observe(viewLifecycleOwner) {
            if (it != null) {
                adapter.setData(it)
                adapter.notifyDataSetChanged()
            }
        }
    }

}