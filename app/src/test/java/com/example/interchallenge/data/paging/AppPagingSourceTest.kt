package com.example.interchallenge.data.paging

import androidx.paging.PagingSource
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.core.constants.INITIAL_PAGE
import com.example.interchallenge.core.constants.TEST_NEXT_PAGE
import com.example.interchallenge.data.model.PullRequestResponse
import com.example.interchallenge.data.model.SearchResponse
import com.example.interchallenge.data.model.toDomainModel
import com.example.interchallenge.data.service.AppService
import com.example.interchallenge.data.util.pullRequestResponseMock
import com.example.interchallenge.data.util.repositoryResponseMock
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppPagingSourceTest {

    private val searchResponseMock = SearchResponse(
        items = listOf(repositoryResponseMock, repositoryResponseMock)
    )

    private val fakeService = object : AppService {
        override suspend fun getJavaRepositories(
            query: String,
            sort: String,
            pageSize: Int,
            page: Int
        ): SearchResponse = searchResponseMock

        override suspend fun getPullRequestsFromRepo(
            owner: String,
            repo: String,
            state: String
        ): List<PullRequestResponse> = listOf(pullRequestResponseMock)
    }

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
    }

    @Test
    fun `should get same amount of result as specified in paging load`() = runTest {
        val response = fakeService.getJavaRepositories(
            query = EMPTY_STRING,
            sort = EMPTY_STRING,
            page = INITIAL_PAGE,
            pageSize = INITIAL_PAGE
        ).items.map { it.toDomainModel() }

        val pagingSource = AppPagingSource(fakeService)

        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = TEST_NEXT_PAGE
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = searchResponseMock.items.size,
                    placeholdersEnabled = false
                )
            )
        )
    }
}