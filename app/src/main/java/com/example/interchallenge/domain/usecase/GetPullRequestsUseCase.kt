package com.example.interchallenge.domain.usecase

import com.example.interchallenge.data.repository.AppRepository
import com.example.interchallenge.domain.model.PullRequest
import kotlinx.coroutines.flow.Flow

class GetPullRequestsUseCase(
    private val repository: AppRepository
) {
    suspend operator fun invoke(owner: String, repo: String): Flow<List<PullRequest>> {
        return this.repository.getPullRequests(owner, repo)
    }
}