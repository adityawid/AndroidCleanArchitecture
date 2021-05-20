package com.adityawidayanto.movies.view.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    val favMovies = movieUseCase.getPagingFavoriteMovie()

}