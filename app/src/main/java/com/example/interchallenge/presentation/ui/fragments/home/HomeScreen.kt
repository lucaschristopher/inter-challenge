package com.example.interchallenge.presentation.ui.fragments.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.interchallenge.presentation.model.RepositoryUiModel
import com.example.interchallenge.presentation.ui.components.core.ErrorDialog
import com.example.interchallenge.presentation.ui.components.core.LoadingComponent
import com.example.interchallenge.presentation.ui.components.core.LoadingItem
import com.example.interchallenge.presentation.ui.components.core.showToast
import com.example.interchallenge.presentation.ui.components.home.RepoItem

@Composable
fun HomeScreen(
    javaRepositories: LazyPagingItems<RepositoryUiModel>,
    openPullRequestDetail: (String, String) -> Unit
) {
    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(items = javaRepositories) { repo ->
            repo?.let {
                RepoItem(
                    repository = repo,
                    openPullRequestDetail = openPullRequestDetail
                )
            }
        }

        when (javaRepositories.loadState.append) {
            is LoadState.NotLoading -> Unit
            LoadState.Loading -> {
                item { LoadingItem() }
            }
            is LoadState.Error -> {
                showToast(context)
            }
        }
    }

    javaRepositories.apply {
        when (loadState.refresh) {
            is LoadState.Loading -> LoadingComponent()
            is LoadState.Error -> ErrorDialog(
                onClick = { retry() }
            )
            else -> Unit
        }
    }
}
