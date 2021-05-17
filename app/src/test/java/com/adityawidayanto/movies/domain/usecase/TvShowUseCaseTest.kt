package com.adityawidayanto.movies.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.adityawidayanto.core.utils.Result
import com.adityawidayanto.db.TvShowDao
import com.adityawidayanto.db.entity.TvShow
import com.adityawidayanto.movies.data.bean.responses.TvShowListBean
import com.adityawidayanto.movies.domain.repository.TvShowRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class TvShowUseCaseTest {

    private var repository = mock(TvShowRepository::class.java)
    private lateinit var useCase: TvShowUseCase

    @Mock
    lateinit var tvShowDao: TvShowDao

    @Before
    fun setUp() {
        useCase = TvShowUseCase(repository)
    }

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

    @Test
    fun `should show tv show list response`() {
        val returnValue = Result.Success(tvShowList)
        val request = runBlocking {
            `when`(repository.getPopularTvShow(1, 10)).thenReturn(
                Result.Success(tvShowList)
            )
            useCase.invoke()
        }
        assertEquals(returnValue, request)
    }

    @Test
    fun `insert favorite tv show into local db and get data`() {
        val returnValue: LiveData<PagingData<TvShow>> = Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { tvShowDao.getAllFavTvShow() }
        ).liveData
        runBlocking {
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
        val request = runBlocking {
            `when`(
                repository.getPagingFavoriteTvShow(
                )
            ).thenReturn(
                returnValue
            )
            useCase.getPagingFavoriteTvShow()
        }
        assertEquals(returnValue, request)
    }

}