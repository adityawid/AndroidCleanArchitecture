package com.adityawidayanto.moviesshow.view.ui.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adityawidayanto.db.bean.TvShow
import com.adityawidayanto.moviesshow.domain.usecase.TvShowUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class TvShowViewModel
@Inject constructor(
    private val tvShowUseCase: TvShowUseCase
) : ViewModel(), CoroutineScope {
    private var job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    private var _tvShowList = MutableLiveData<List<TvShow>>()
    val tvShowList: LiveData<List<TvShow>> get() = _tvShowList

    fun getMovies() {
        launch {
            _tvShowList.value = tvShowUseCase.execute()
        }
    }
}