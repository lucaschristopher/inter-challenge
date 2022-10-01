package com.example.interchallenge.domain.usecase

data class AppUseCase(
    val getJavaRepositoriesUseCase: GetJavaRepositoriesUseCase,
    val getPullRequestsUseCase: GetPullRequestsUseCase
)