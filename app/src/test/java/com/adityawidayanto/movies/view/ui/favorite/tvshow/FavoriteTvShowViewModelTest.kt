package com.adityawidayanto.movies.view.ui.favorite.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.paging.*
import androidx.recyclerview.widget.ListUpdateCallback
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.db.TvShowDao
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.repository.TvShowRepositoryImpl
import com.adityawidayanto.movies.data.repository.tvshow.TvShowLocalDataSource
import com.adityawidayanto.movies.data.repository.tvshow.TvShowRemoteDataSource
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import com.adityawidayanto.movies.domain.usecase.TvShowUseCase
import com.google.common.truth.Truth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FavoriteTvShowViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowUseCase: TvShowUseCase

    private lateinit var viewModel: FavoriteTvShowViewModel

    @Mock
    lateinit var tvShowDao: TvShowDao

    @Mock
    lateinit var repository: TvShowRepository

    @ExperimentalCoroutinesApi
    private val provider = CoroutineTestRule().testDispatcherProvider.unconfined

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Mock
    lateinit var remoteDataSource: TvShowRemoteDataSource

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(provider)

        viewModel = FavoriteTvShowViewModel(tvShowUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `should return a paging response of favorite movies data`() = runBlockingTest(provider) {
        val tvShows = listOf(
            TvShow(
                "firstAirDate",
                1,
                "name",
                "overview",
                "path",
                "backDrop",
                2000.1,
                9.0,
                50010
            ),
            TvShow(
                "firstAirDate2",
                2,
                "name 2",
                "overview 2",
                "path",
                "backDrop",
                2000.1,
                9.0,
                50010
            ),
            TvShow(
                "firstAirDate",
                3,
                "name",
                "overview",
                "path",
                "backDrop",
                2000.1,
                9.0,
                50010
            )
        )

        val fakeDao = TvShowDaoFake(tvShows)

        val fakeLocalData = TvShowLocalDataSource(fakeDao)
        val fakeRepository = TvShowRepositoryImpl(
            coroutineTestRule.testDispatcherProvider,
            remoteDataSource, fakeLocalData
        )
        val fakeUseCase = TvShowUseCase(fakeRepository)

        val fakeVm = FavoriteTvShowViewModel(fakeUseCase)
        val differ = AsyncPagingDataDiffer(
            diffCallback = TvShowPagingAdapter.listItemCallback,
            updateCallback = noopListUpdateCallback,
            mainDispatcher = provider,
            workerDispatcher = provider,
        )
        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))

        val job = launch {
            fakeVm.favTvShows.observeForever {
                differ.submitData(lifecycle, it)
            }
        }

        advanceUntilIdle()
        println("test fake " + differ.snapshot())
        Truth.assertThat(differ.snapshot()).containsExactly(
            tvShows[0],
            tvShows[1],
            tvShows[2]
        )
        job.cancel()
    }

    @Test
    fun `should return a response of favorite tv show data`() = runBlockingTest {
        runBlockingTest {
            repository.addTvShowFavorite(
                TvShow(
                    "firstAirDate",
                    1,
                    "name",
                    "overview",
                    "path",
                    "backDrop",
                    2000.1,
                    9.0,
                    50010
                )
            )
        }

        val returnValue = Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { tvShowDao.getAllFavTvShow() }
        ).liveData

        Mockito.`when`(
            tvShowUseCase.getPagingFavoriteTvShow()
        ).thenReturn(
            returnValue
        )


        val req = tvShowUseCase.getPagingFavoriteTvShow()

        advanceUntilIdle()
        Assert.assertEquals(returnValue, req)
        Mockito.clearInvocations(tvShowUseCase)
    }

}

val noopListUpdateCallback = object : ListUpdateCallback {
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
    override fun onMoved(fromPosition: Int, toPosition: Int) {}
    override fun onChanged(position: Int, count: Int, payload: Any?) {}
}

class TvShowDaoFake(val tvshows: List<TvShow>) : TvShowDao() {
    override suspend fun insertAll(movies: List<TvShow>) {
    }

    override suspend fun insert(tvShow: TvShow) {
    }

    override fun getAllFavTvShow(): PagingSource<Int, TvShow> {
        return object : PagingSource<Int, TvShow>() {
            override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvShow> {
                return LoadResult.Page(
                    data = tvshows,
                    prevKey = null,
                    nextKey = null,
                )
            }

            // Ignored in test.
            override fun getRefreshKey(state: PagingState<Int, TvShow>): Int? = null
        }
    }

    override suspend fun checkIdTvShow(id: Int): Int = 1

    override suspend fun deleteFavorite(id: Int) {
    }

    override suspend fun deleteAll() {
    }

}