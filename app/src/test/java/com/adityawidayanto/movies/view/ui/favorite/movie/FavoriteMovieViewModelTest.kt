package com.adityawidayanto.movies.view.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import androidx.recyclerview.widget.ListUpdateCallback
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.db.MovieDao
import com.adityawidayanto.db.entity.Movie
import com.adityawidayanto.movies.data.repository.MovieRepositoryImpl
import com.adityawidayanto.movies.data.repository.movie.MovieLocalDataSource
import com.adityawidayanto.movies.data.repository.movie.MoviePagingSource
import com.adityawidayanto.movies.data.repository.movie.MovieRemoteDataSource
import com.adityawidayanto.movies.domain.repository.MovieRepository
import com.adityawidayanto.movies.domain.usecase.MovieUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class FavoriteMovieViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieUseCase: MovieUseCase

    private lateinit var viewModel: FavoriteMovieViewModel

    @Mock
    lateinit var movieDao: MovieDao

    @Mock
    lateinit var repository: MovieRepository

    @ExperimentalCoroutinesApi
    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var remoteDataSource: MovieRemoteDataSource

    @Mock
    lateinit var moviePagingSource: MoviePagingSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(provider)

        viewModel = FavoriteMovieViewModel(movieUseCase)
    }

    @Test
    fun `should return a paging response of favorite movies data`() = runBlockingTest(provider) {
        val movies = listOf(
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
            ),
            Movie(
                2,
                "overview 2",
                "path 2",
                "release 2",
                "title 2",
                "backDrop 2",
                5000.1,
                7.9,
                2000
            ),
            Movie(
                3,
                "overview 3",
                "path 3",
                "release 3",
                "title",
                "backDrop",
                5000.1,
                7.9,
                2000
            )
        )
        val fakeDao = MovieDaoFake(movies)

        val fakeLocalData = MovieLocalDataSource(fakeDao)
        val fakeRepository = MovieRepositoryImpl(
            coroutineTestRule.testDispatcherProvider,
            remoteDataSource, moviePagingSource, fakeLocalData
        )
        val fakeUseCase = MovieUseCase(fakeRepository)

        val fakeVm = FavoriteMovieViewModel(fakeUseCase)
        val differ = AsyncPagingDataDiffer(
            diffCallback = MoviePagingAdapter.listItemCallback,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = provider,
            workerDispatcher = provider,
        )
        val job = launch {
            fakeVm.favMovies.collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        assertThat(differ.snapshot()).containsExactly(
            movies[0],
            movies[1],
            movies[2]
        )
        job.cancel()
    }

    val noopListUpdateCallback = object : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }

    @Test
    fun `should return a flow of favorite movies data`() = runBlockingTest {
        runBlockingTest {
            repository.addMovieFavorite(
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
        }

        val returnValue = Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { movieDao.getAllFavMovie() }
        ).flow

        Mockito.`when`(
            movieUseCase.getPagingFavoriteMovie(
            )
        ).thenReturn(
            returnValue
        )


        val req = movieUseCase.getPagingFavoriteMovie()

        advanceUntilIdle()
        Assert.assertEquals(returnValue, req)
        Mockito.clearInvocations(movieUseCase)
    }


    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

class MovieDaoFake(val movies: List<Movie>) : MovieDao() {
    override suspend fun insertAll(movies: List<Movie>) {

    }

    override suspend fun insert(movies: Movie) {
    }

    override suspend fun checkIdMovie(id: Int): Int = 1

    override fun getAllFavMovie(): PagingSource<Int, Movie> {
        return object : PagingSource<Int, Movie>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
                return LoadResult.Page(
                    data = movies,
                    prevKey = null,
                    nextKey = null,
                )
            }

            // Ignored in test.
            override fun getRefreshKey(state: PagingState<Int, Movie>): Int? = null
        }
    }

    override suspend fun deleteFavorite(id: Int) {
    }

    override suspend fun deleteAll() {
    }
}
