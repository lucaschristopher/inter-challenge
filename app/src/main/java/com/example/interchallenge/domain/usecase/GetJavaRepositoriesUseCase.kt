package com.example.interchallenge.domain.usecase

import androidx.paging.PagingData
import com.example.interchallenge.data.repository.AppRepository
import com.example.interchallenge.domain.model.Repository
import kotlinx.coroutines.flow.Flow

class GetJavaRepositoriesUseCase(
    private val repository: AppRepository
) {
    operator fun invoke(): Flow<PagingData<Repository>> {
        return this.repository.getJavaRepositories()
    }
}