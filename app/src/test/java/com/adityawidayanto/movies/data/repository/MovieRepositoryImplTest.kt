package com.adityawidayanto.movies.data.repository

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.ListUpdateCallback
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.core.utils.test.runBlockingTest
import com.adityawidayanto.db.MovieDao
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.bean.responses.MovieListBean
import com.adityawidayanto.movies.data.repository.movie.MovieLocalDataSource
import com.adityawidayanto.movies.data.repository.movie.MoviePagingSource
import com.adityawidayanto.movies.data.repository.movie.MovieRemoteDataSource
import com.adityawidayanto.movies.view.ui.favorite.movie.MovieDaoFake
import com.adityawidayanto.movies.view.ui.favorite.movie.MoviePagingAdapter
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieRepositoryImplTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: MovieRepositoryImpl

    @Mock
    lateinit var remoteDataSource: MovieRemoteDataSource

    @Mock
    lateinit var moviePagingSource: MoviePagingSource

    companion object {
        private val movies = listOf(
            Movie(
                1,
                "overview",
                "path",
                "release",
                "title",
                "backDrop",
                5000.1,
                7.9,
                2000
            )
        )

        private val moviesData = MovieListBean(movies)

    }

    @Mock
    lateinit var movieDao: MovieDao

    @Mock
    lateinit var movieLocalDataSource: MovieLocalDataSource

    @ExperimentalCoroutinesApi
    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined
    val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = MovieRepositoryImpl(
            coroutineTestRule.testDispatcherProvider,
            remoteDataSource,
            moviePagingSource,
            movieLocalDataSource = movieLocalDataSource
        )
    }

    @Test
    fun `when fetch movie from api, get movie from local`() = runBlockingTest {
        val fakeDao = MovieDaoFake(movies)

        val fakeLocalData = MovieLocalDataSource(fakeDao)
        val fakeRepository = MovieRepositoryImpl(
            coroutineTestRule.testDispatcherProvider,
            remoteDataSource, moviePagingSource, fakeLocalData
        )
        val differ = AsyncPagingDataDiffer(
            diffCallback = MoviePagingAdapter.listItemCallback,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = provider,
            workerDispatcher = provider,
        )
        val job = launch {
            fakeRepository.getPagingFavoriteMovie().collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        println("test Adit " + differ.snapshot())
        Truth.assertThat(differ.snapshot()).containsExactly(
            movies[0]
        )
        job.cancel()
    }

    @Test
    fun `when fetch movie from api, get movie from network should be invoked`() =
        coroutineTestRule.runBlockingTest {
            val returnValue = Result.Success(moviesData)
            `when`(
                remoteDataSource.getPopularMovies(
                    coroutineTestRule.testDispatcher,
                    1, 10
                )
            ).thenReturn(returnValue)
            val response = repository.getPopularMovie(1, 10)
            assertEquals(returnValue, response)
        }

    @Test
    fun `insert Favorite movie into local db and get favorite movie`() =
        coroutineTestRule.runBlockingTest {
            val returnValue = Pager(
                config = PagingConfig(
                    pageSize = 10
                ),
                pagingSourceFactory = { movieDao.getAllFavMovie() }
            ).flow
            movieLocalDataSource.addFavoriteMovie(
                Movie(
                    1,
                    "overview",
                    "path",
                    "release",
                    "title",
                    "backDrop",
                    5000.1,
                    7.9,
                    2000
                )
            )
            `when`(
                movieLocalDataSource.getFavMovies()
            ).thenReturn(returnValue)
            val response = repository.getPagingFavoriteMovie()
            assertEquals(returnValue, response)
        }


}