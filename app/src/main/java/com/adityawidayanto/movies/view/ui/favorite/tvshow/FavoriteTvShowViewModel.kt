package com.adityawidayanto.movies.view.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.adityawidayanto.movies.domain.usecase.TvShowUseCase
import javax.inject.Inject

class FavoriteTvShowViewModel @Inject constructor(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel() {
    val favTvShows = tvShowUseCase.getPagingFavoriteTvShow().cachedIn(viewModelScope)
}