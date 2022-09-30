package com.example.interchallenge.presentation.model

class RepositoryUiModel(
    val id: Long,
    val name: String,
    val description: String,
    val url: String,
    val forks: Long,
    val stargazers: Long,
    val owner: OwnerUiModel,
)