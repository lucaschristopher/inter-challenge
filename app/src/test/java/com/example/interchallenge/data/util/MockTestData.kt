package com.example.interchallenge.data.util

import com.example.interchallenge.core.constants.DEFAULT_ID
import com.example.interchallenge.core.constants.EMPTY_QUANTITY
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.data.model.OwnerResponse
import com.example.interchallenge.data.model.PullRequestResponse
import com.example.interchallenge.data.model.RepositoryResponse
import com.example.interchallenge.data.model.SearchResponse
import com.example.interchallenge.domain.model.Owner
import com.example.interchallenge.domain.model.PullRequest
import com.example.interchallenge.domain.model.Repository
import com.example.interchallenge.domain.model.toUiModel

val ownerMock = Owner(
    id = DEFAULT_ID,
    login = EMPTY_STRING,
    avatarUrl = EMPTY_STRING
)

val repositoryMock = Repository(
    id = DEFAULT_ID,
    name = "LeetCodeAnimation",
    description = "sahsuahsiuahsiashaisuhaishaisuhaisahs",
    url = EMPTY_STRING,
    forks = EMPTY_QUANTITY,
    stargazers = EMPTY_QUANTITY,
    owner = ownerMock
)

val repositoryUiModelMock = repositoryMock.toUiModel()

val ownerResponseMock = OwnerResponse(
    id = DEFAULT_ID,
    login = EMPTY_STRING,
    avatarUrl = EMPTY_STRING
)

val repositoryResponseMock = RepositoryResponse(
    id = DEFAULT_ID,
    name = EMPTY_STRING,
    description = EMPTY_STRING,
    url = EMPTY_STRING,
    forks = EMPTY_QUANTITY,
    stargazers = EMPTY_QUANTITY,
    owner = ownerResponseMock
)

val pullRequestResponseMock = PullRequestResponse(
    id = DEFAULT_ID,
    url = EMPTY_STRING,
    state = EMPTY_STRING,
    title = EMPTY_STRING,
    body = EMPTY_STRING,
    updatedAt = EMPTY_STRING,
    user = ownerResponseMock
)

val pullRequestMock = PullRequest(
    id = DEFAULT_ID,
    url = EMPTY_STRING,
    state = EMPTY_STRING,
    title = EMPTY_STRING,
    body = EMPTY_STRING,
    updatedAt = EMPTY_STRING,
    user = ownerMock
)
