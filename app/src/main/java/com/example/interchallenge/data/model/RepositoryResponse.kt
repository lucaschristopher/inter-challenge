package com.example.interchallenge.data.model

import com.example.interchallenge.core.constants.EMPTY_QUANTITY
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.domain.model.Repository
import com.google.gson.annotations.SerializedName

class RepositoryResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("html_url") val url: String,
    @SerializedName("forks_count") val forks: Long?,
    @SerializedName("stargazers_count") val stargazers: Long?,
    @SerializedName("owner") val owner: OwnerResponse,
)

fun RepositoryResponse.toDomainModel() = Repository(
    id = this.id,
    name = this.name,
    description = this.description ?: EMPTY_STRING,
    url = this.url,
    forks = this.forks ?: EMPTY_QUANTITY,
    stargazers = this.stargazers ?: EMPTY_QUANTITY,
    owner = this.owner.toDomainModel()
)