package com.example.interchallenge.data.model

import com.example.interchallenge.domain.model.Owner
import com.google.gson.annotations.SerializedName

class OwnerResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String,
)

fun OwnerResponse.toDomainModel() = Owner(
    id = this.id,
    login = this.login,
    avatarUrl = this.avatarUrl
)