package com.adityawidayanto.movies.view.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase
) : ViewModel() {
    private val _detail = MutableLiveData<DetailBean>()
    val detail: LiveData<DetailBean>
        get() = _detail

    fun initDetail(detailBean: DetailBean) {
        _detail.value = detailBean
    }

    fun addFavorite(movie: Movie) {
        viewModelScope.launch {
            movieUseCase.addMovieFavorite(movie)
        }
    }
}