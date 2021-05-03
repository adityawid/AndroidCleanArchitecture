package com.adityawidayanto.movies.view.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMovieViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _error = MutableLiveData<Result.Error>()
    val error: LiveData<Result.Error>
        get() = _error

    fun getPopularMovie() {
        viewModelScope.launch {
            when (val result = movieUseCase()) {

                is Result.Success -> {
                    println("test Adit sukses " + result.data)
                    _movieList.value = result.data.movies
                }
                is Result.Error -> {
                    println("test Adit error" + result.errorMessage)
                    _error.value =
                        Result.Error(result.cause, result.code, result.errorMessage)
                }
            }
        }
    }
}