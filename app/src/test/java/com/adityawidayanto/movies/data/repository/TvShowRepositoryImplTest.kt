package com.adityawidayanto.movies.data.repository

import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.core.utils.test.CoroutineTestRule
import com.adityawidayanto.core.utils.test.runBlockingTest
import com.adityawidayanto.db.TvShowDao
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean
import com.adityawidayanto.movies.data.repository.tvshow.TvShowLocalDataSource
import com.adityawidayanto.movies.data.repository.tvshow.TvShowRemoteDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class TvShowRepositoryImplTest {
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var repository: TvShowRepositoryImpl

    @Mock
    lateinit var remoteDataSource: TvShowRemoteDataSource

    @Mock
    lateinit var tvShowLocalDataSource: TvShowLocalDataSource

    @Mock
    lateinit var tvShowDao: TvShowDao

    companion object {
        private val tvshow = listOf(
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
        private var tvShowList: TvShowListBean = TvShowListBean(tvshow)

    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = TvShowRepositoryImpl(
            coroutineTestRule.testDispatcherProvider,
            remoteDataSource,
            tvShowLocalDataSource = tvShowLocalDataSource
        )
    }

    @Test
    fun `when fetch tv show from api, get tv show from network should be invoked`() =
        coroutineTestRule.runBlockingTest {
            val returnValue = Result.Success(tvShowList)
            `when`(
                remoteDataSource.getPopularTvShow(
                    coroutineTestRule.testDispatcher,
                    1, 10
                )
            ).thenReturn(returnValue)
            val response = repository.getPopularTvShow(1, 10)
            assertEquals(returnValue, response)


        }


}