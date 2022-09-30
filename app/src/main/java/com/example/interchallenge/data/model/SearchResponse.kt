package com.example.interchallenge.data.model

import com.example.interchallenge.domain.model.SearchModel
import com.google.gson.annotations.SerializedName

class SearchResponse(
    @SerializedName("items") val items: List<RepositoryResponse>
)

fun SearchResponse.toDomainModel() =
    SearchModel(items = items.map { it.toDomainModel() })