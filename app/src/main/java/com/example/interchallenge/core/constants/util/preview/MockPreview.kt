package com.example.interchallenge.core.constants.util.preview

import com.example.interchallenge.core.constants.DEFAULT_ID
import com.example.interchallenge.core.constants.EMPTY_QUANTITY
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.presentation.model.OwnerUiModel
import com.example.interchallenge.presentation.model.RepositoryUiModel

val ownerUiModel = OwnerUiModel(
    id = DEFAULT_ID,
    login = EMPTY_STRING,
    avatarUrl = EMPTY_STRING,
)

val repositoryUiModel = RepositoryUiModel(
    id = DEFAULT_ID,
    name = "LeetCodeAnimation",
    description = "sahsuahsiuahsiashaisuhaishaisuhaisahs",
    url = EMPTY_STRING,
    forks = EMPTY_QUANTITY,
    stargazers = EMPTY_QUANTITY,
    owner = ownerUiModel
)