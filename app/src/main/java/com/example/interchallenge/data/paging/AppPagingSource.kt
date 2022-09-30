package com.example.interchallenge.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.interchallenge.core.constants.INITIAL_PAGE
import com.example.interchallenge.core.constants.PAGE_SIZE
import com.example.interchallenge.core.constants.QUERY_PARAM
import com.example.interchallenge.core.constants.SORT_PARAM
import com.example.interchallenge.data.model.toDomainModel
import com.example.interchallenge.data.service.AppService
import com.example.interchallenge.domain.model.Repository

class AppPagingSource(
    private val service: AppService
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        return try {
            val nextPage = params.key ?: INITIAL_PAGE
            val repoList = service.getJavaRepositories(
                query = QUERY_PARAM,
                sort = SORT_PARAM,
                pageSize = PAGE_SIZE,
                page = nextPage
            )
            LoadResult.Page(
                data = repoList.items.map { it.toDomainModel() },
                prevKey = params.key,
                nextKey = params.key?.plus(ONE) ?: STARTING_PAGE_INDEX.plus(ONE)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = INITIAL_PAGE
        const val ONE = 1
    }
}