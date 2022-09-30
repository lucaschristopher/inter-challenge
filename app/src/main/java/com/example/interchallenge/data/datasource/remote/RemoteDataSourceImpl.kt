package com.example.interchallenge.data.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.interchallenge.core.constants.ALL_FLAG
import com.example.interchallenge.core.constants.PAGE_SIZE
import com.example.interchallenge.core.constants.util.error.mapToCustomError
import com.example.interchallenge.data.model.PullRequestResponse
import com.example.interchallenge.data.paging.AppPagingSource
import com.example.interchallenge.data.service.AppService
import com.example.interchallenge.domain.model.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSourceImpl(
    private val service: AppService
) : RemoteDataSource {

    override fun getJavaRepositories(): Flow<PagingData<Repository>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                AppPagingSource(this.service)
            }
        ).flow
    }

    override suspend fun getPullRequets(owner: String, repo: String): Flow<List<PullRequestResponse>> =
        flow {
            val response = service.getPullRequestsFromRepo(
                owner = owner,
                repo = repo,
                state = ALL_FLAG
            )
            emit(response)
        }.mapToCustomError()
}