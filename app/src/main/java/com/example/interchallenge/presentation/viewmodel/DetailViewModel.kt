package com.example.interchallenge.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interchallenge.domain.model.PullRequest
import com.example.interchallenge.domain.model.toUiModel
import com.example.interchallenge.domain.usecase.AppUseCase
import com.example.interchallenge.presentation.model.PullRequestUiModel
import com.example.interchallenge.presentation.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val appUseCase: AppUseCase
) : ViewModel() {

    private val _pullRequests =
        MutableStateFlow<ViewState<List<PullRequestUiModel>>>(ViewState.Initial)
    val pullRequests: StateFlow<ViewState<List<PullRequestUiModel>>>
        get() = _pullRequests

    fun getPullRequests(owner: String, repo: String) {
        viewModelScope.launch {
            appUseCase.getPullRequestsUseCase(owner, repo)
                .onStart { handleLoading() }
                .catch { handleError(it) }
                .collect { handleSuccess(it) }
        }
    }

    private fun handleLoading() {
        _pullRequests.value = ViewState.Loading
    }

    private fun handleError(throwable: Throwable) {
        _pullRequests.value = ViewState.Error(throwable)
    }

    private fun handleSuccess(pullRequests: List<PullRequest>) {
        _pullRequests.value = ViewState.Success(pullRequests.map { it.toUiModel() })
    }
}