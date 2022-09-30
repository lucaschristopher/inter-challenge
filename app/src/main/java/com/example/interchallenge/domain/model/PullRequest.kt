package com.example.interchallenge.domain.model

import com.example.interchallenge.presentation.model.PullRequestUiModel

class PullRequest(
    val id: Long,
    val url: String,
    val state: String,
    val title: String,
    val body: String,
    val updatedAt: String,
    val user: Owner
)

fun PullRequest.toUiModel() = PullRequestUiModel(
    id = this.id,
    url = this.url,
    state = this.state,
    title = this.title,
    body = this.body,
    updatedAt = this.updatedAt,
    user = this.user.toUiModel()
)
