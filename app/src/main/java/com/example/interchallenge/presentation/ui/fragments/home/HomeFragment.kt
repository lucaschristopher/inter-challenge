package com.example.interchallenge.presentation.ui.fragments.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.interchallenge.presentation.ui.components.home.HomeTopBar
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
        topBar = { HomeTopBar() },
        content = {
            HomeScreen(
                javaRepositories = javaRepositories,
                openPullRequestDetail = openPullRequestDetail
            )
        }
    )
}