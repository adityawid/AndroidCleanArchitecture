package com.adityawidayanto.movies.view.ui.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.core.utils.test.LifeCycleTestOwner
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.response.MovieListBean
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class PopularMovieViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    private lateinit var lifecycleOwner: LifeCycleTestOwner

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    private lateinit var observer: Observer<Result<List<Movie>>>
//===========///

    @Mock
    lateinit var result: Observer<List<Movie>>

    @Mock
    lateinit var error: Observer<String>

    @Captor
    lateinit var argResultCaptor: ArgumentCaptor<List<Movie>>

    @Captor
    lateinit var argErrorCaptor: ArgumentCaptor<String>

    private lateinit var viewModel: PopularMovieViewModel

    private val movies = listOf(
        Movie(
            1,
            "overview",
            "path",
            "release",
            "title",
        )
    )

    private val moviesData = MovieListBean(movies)

    private val schedulerProvider = CoroutineTestRule().testDispatcherProvider.unconfined


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(schedulerProvider)

        viewModel = PopularMovieViewModel(movieUseCase)
        viewModel.movieList.observeForever(result)
//        viewModel.error.observeForever(error)
    }

    @Test
    fun `should return a response of movies data`() = runBlocking {
        val returnValue = Result.Success(moviesData)
        `when`(movieUseCase.invoke()).thenReturn(returnValue)
        viewModel.getPopularMovie()
        verify(result, atLeastOnce()).onChanged(argResultCaptor.capture())
        Assert.assertEquals(returnValue.data.movies, argResultCaptor.allValues.first())
        clearInvocations(movieUseCase, result)
    }
}