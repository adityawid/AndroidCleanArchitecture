package com.adityawidayanto.moviesshow.view.ui.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adityawidayanto.moviesshow.domain.usecase.TvShowUseCase


class TvShowViewModelFactory(
    private val tvShowUseCase: TvShowUseCase,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowViewModel(tvShowUseCase) as T
    }

}