package com.adityawidayanto.moviesshow.view.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adityawidayanto.db.bean.Movie
import com.adityawidayanto.moviesshow.domain.usecase.MoviesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class MoviesViewModel
@Inject constructor(
    private val getMoviesUseCase: MoviesUseCase
) : ViewModel(), CoroutineScope {
    private var job = SupervisorJob()
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

//    private val movieRepository = MovieRepositoryImpl()
//        private val getMoviesUseCase: MoviesUseCase = MoviesUseCase(movieRepository)

    private var _movieList = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = _movieList

    fun getMovies() {
        launch {
            _movieList.value = getMoviesUseCase.execute()
        }
    }
}