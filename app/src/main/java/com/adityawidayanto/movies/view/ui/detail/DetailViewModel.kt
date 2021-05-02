package com.adityawidayanto.movies.view.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adityawidayanto.movies.data.bean.DetailBean
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
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
}