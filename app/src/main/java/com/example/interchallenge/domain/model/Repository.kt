package com.example.interchallenge.domain.model

import com.example.interchallenge.presentation.model.RepositoryUiModel

class Repository(
    val id: Long,
    val name: String,
    val description: String,
    val url: String,
    val forks: Long,
    val stargazers: Long,
    val owner: Owner,
)

fun Repository.toUiModel() = RepositoryUiModel(
    id = this.id,
    name = this.name,
    description = this.description,
    url = this.url,
    forks = this.forks,
    stargazers = this.stargazers,
    owner = this.owner.toUiModel()
)