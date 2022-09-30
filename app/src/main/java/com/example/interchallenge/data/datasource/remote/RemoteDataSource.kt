package com.example.interchallenge.data.datasource.remote

import androidx.paging.PagingData
import com.example.interchallenge.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getJavaRepositories(): Flow<PagingData<Repository>>
}