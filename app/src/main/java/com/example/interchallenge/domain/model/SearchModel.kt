package com.example.interchallenge.domain.model

import com.example.interchallenge.presentation.model.SearchUiModel

class SearchModel(
    val items: List<Repository>
)

fun SearchModel.toUiModel() = SearchUiModel(
    items = this.items.map { it.toUiModel() }
)