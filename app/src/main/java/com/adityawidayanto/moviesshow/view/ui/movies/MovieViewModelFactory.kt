package com.adityawidayanto.moviesshow.view.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adityawidayanto.moviesshow.domain.usecase.MoviesUseCase


class MovieViewModelFactory(
    private val getMoviesUseCase: MoviesUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(getMoviesUseCase) as T
    }

}