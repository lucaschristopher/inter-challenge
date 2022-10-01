package com.example.interchallenge.domain.model

import com.example.interchallenge.presentation.model.OwnerUiModel

data class Owner(
    val id: Long,
    val login: String,
    val avatarUrl: String
)

fun Owner.toUiModel() = OwnerUiModel(
    id = this.id,
    login = this.login,
    avatarUrl = this.avatarUrl
)