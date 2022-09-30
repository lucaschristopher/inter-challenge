package com.example.interchallenge.data.repository

import androidx.paging.PagingData
import com.example.interchallenge.domain.model.Repository
import kotlinx.coroutines.flow.Flow

interface AppRepository {
    fun getJavaRepositories(): Flow<PagingData<Repository>>
}