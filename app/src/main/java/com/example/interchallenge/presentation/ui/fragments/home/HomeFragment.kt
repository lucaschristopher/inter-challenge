package com.example.interchallenge.presentation.ui.fragments.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.interchallenge.R
import com.example.interchallenge.presentation.ui.components.core.AppTopBar
import com.example.interchallenge.presentation.ui.theme.WhiteAccent
import com.example.interchallenge.presentation.viewmodel.AppViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeFragment(
    openPullRequestDetail: (String, String) -> Unit
) {

    val viewModel = getViewModel<AppViewModel>()
    val javaRepositories = viewModel.getJavaRepositories().collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WhiteAccent),
        topBar = {
            AppTopBar(
                title = stringResource(id = R.string.home_title),
                showBackButton = false,
                navigateBack = { }
            )
        },
        content = {
            HomeScreen(
                javaRepositories = javaRepositories,
                openPullRequestDetail = openPullRequestDetail
            )
        }
    )
}