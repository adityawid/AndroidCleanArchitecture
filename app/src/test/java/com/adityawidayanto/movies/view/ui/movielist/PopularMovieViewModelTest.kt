package com.adityawidayanto.movies.view.ui.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.adityawidayanto.core.network.HttpResult
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.response.MovieListBean
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
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

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    @Mock
    lateinit var result: Observer<List<Movie>>

    @Mock
    lateinit var error: Observer<Result.Error>

    @Captor
    lateinit var argResultCaptor: ArgumentCaptor<List<Movie>>

    @Captor
    lateinit var argErrorCaptor: ArgumentCaptor<Result.Error>

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

    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(provider)

        viewModel = PopularMovieViewModel(movieUseCase)
        viewModel.movieList.observeForever(result)
        viewModel.error.observeForever(error)
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


    @Test
    fun `should return an error bad response`() = runBlocking {
        val returnValue = Result.Error(HttpResult.BAD_RESPONSE, 400, "Bad Response")
        `when`(movieUseCase.invoke()).thenReturn(returnValue)
        viewModel.getPopularMovie()
        verify(error, atLeastOnce()).onChanged(argErrorCaptor.capture())
        Assert.assertEquals(returnValue, argErrorCaptor.allValues.first())
        clearInvocations(movieUseCase, error)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}