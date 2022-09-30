package com.example.interchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.interchallenge.domain.model.toUiModel
import com.example.interchallenge.domain.usecase.AppUseCase
import com.example.interchallenge.presentation.model.RepositoryUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AppViewModel(
    private val appUseCase: AppUseCase
) : ViewModel() {

    fun getJavaRepositories(): Flow<PagingData<RepositoryUiModel>> = flow {
        appUseCase.getJavaRepositoriesUseCase().collect {
            emit(it.map { repo -> repo.toUiModel() })
        }
    }.cachedIn(viewModelScope)
}