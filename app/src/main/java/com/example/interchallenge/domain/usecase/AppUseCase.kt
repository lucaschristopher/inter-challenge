package com.example.interchallenge.domain.usecase

class AppUseCase(
    val getJavaRepositoriesUseCase: GetJavaRepositoriesUseCase,
    val getPullRequestsUseCase: GetPullRequestsUseCase
)