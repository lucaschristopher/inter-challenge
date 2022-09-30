package com.example.interchallenge.data.model

import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.domain.model.PullRequest
import com.google.gson.annotations.SerializedName

class PullRequestResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("html_url") val url: String,
    @SerializedName("state") val state: String,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String?,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("user") val user: OwnerResponse,
)

fun PullRequestResponse.toDomainModel() = PullRequest(
    id = this.id,
    url = this.url,
    state = this.state,
    title = this.title,
    body = this.body ?: EMPTY_STRING,
    updatedAt = updatedAt,
    user = this.user.toDomainModel()
)