package com.adityawidayanto.moviesshow.view.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adityawidayanto.db.bean.Movie

class MoviesViewModel : ViewModel() {
    private var _movieList = MutableLiveData<Movie>()
    val movieList: LiveData<Movie> get() = _movieList
}