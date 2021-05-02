package com.adityawidayanto.movies.view.ui.tvshowlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.domain.usecase.TvShowUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularTvShowViewModel @Inject constructor(
    private val useCase: TvShowUseCase
) : ViewModel() {

    private val _tvShowList = MutableLiveData<List<TvShow>>()
    val tvShowList: LiveData<List<TvShow>>
        get() = _tvShowList

    private val _error = MutableLiveData<Result.Error>()
    val error: LiveData<Result.Error>
        get() = _error


    fun getPopularTvShow() {
        viewModelScope.launch {
            when (val result = useCase()) {
                is Result.Success -> {
                    _tvShowList.value = result.data.tvShows
                }
                is Result.Error -> {
                    _error.value =
                        Result.Error(result.cause, result.code, result.errorMessage)
                }
            }
        }
    }
}