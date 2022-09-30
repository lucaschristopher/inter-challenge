package com.example.interchallenge.data.repository

import androidx.paging.PagingData
import com.example.interchallenge.data.datasource.remote.RemoteDataSource
import com.example.interchallenge.data.model.toDomainModel
import com.example.interchallenge.domain.model.PullRequest
import com.example.interchallenge.domain.model.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val dispatcher: CoroutineDispatcher
) : AppRepository {

    override fun getJavaRepositories(): Flow<PagingData<Repository>> {
        return this.remoteDataSource.getJavaRepositories().flowOn(dispatcher)
    }

    override suspend fun getPullRequests(owner: String, repo: String): Flow<List<PullRequest>> =
        flow {
            remoteDataSource.getPullRequets(owner, repo).collect { result ->
                emit(result.map { it.toDomainModel() })
            }
        }.flowOn(dispatcher)
}