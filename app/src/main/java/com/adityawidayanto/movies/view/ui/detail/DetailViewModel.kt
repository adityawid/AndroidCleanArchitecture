package com.adityawidayanto.movies.view.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import com.adityawidayanto.movies.domain.usecase.TvShowUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {
    private val _detail = MutableLiveData<DetailBean>()
    val detail: LiveData<DetailBean>
        get() = _detail

    fun initDetail(detailBean: DetailBean) {
        _detail.value = detailBean
    }

    fun addMovieFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.addMovieFavorite(movie)
        }
    }

    fun addTvShowFavorite(tvShow: TvShow) {
        viewModelScope.launch {
            tvShowUseCase.addTvShowFavorite(tvShow)
        }
    }

    fun removeMovieFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.removeMovieFavorite(movie)
        }
    }

    fun removeTvShowFavorite(tvShow: TvShow) {
        viewModelScope.launch {
            tvShowUseCase.removeTvShowFavorite(tvShow)
        }
    }

    suspend fun checkIdMovie(movie: Movie) = movieUseCase.checkMovieFavorite(movie)
    suspend fun checkIdTvShow(tvShow: TvShow) = tvShowUseCase.checkTvShowFavorite(tvShow)
}