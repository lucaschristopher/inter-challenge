package com.example.interchallenge.data.datasource.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.interchallenge.core.constants.PAGE_SIZE
import com.example.interchallenge.data.paging.AppPagingSource
import com.example.interchallenge.data.service.AppService
import com.example.interchallenge.domain.model.Repository
import kotlinx.coroutines.flow.Flow

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
}