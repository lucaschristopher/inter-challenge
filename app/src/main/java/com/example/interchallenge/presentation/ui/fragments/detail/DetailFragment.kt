package com.example.interchallenge.presentation.ui.fragments.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.interchallenge.presentation.state.ViewState
import com.example.interchallenge.presentation.ui.components.core.ErrorDialog
import com.example.interchallenge.presentation.ui.components.core.LoadingComponent
import com.example.interchallenge.presentation.ui.components.detail.DetailTopBar
import com.example.interchallenge.presentation.ui.theme.WhiteAccent
import com.example.interchallenge.presentation.viewmodel.DetailViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailFragment(
    navHostController: NavHostController,
    user: String,
    repo: String
) {

    val viewModel = getViewModel<DetailViewModel>()

    fun launch() {
        viewModel.getPullRequests(user, repo)
    }

    launch()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = WhiteAccent),
        topBar = { DetailTopBar(navHostController = navHostController, repoName = repo) }
    ) {
        when (val response = viewModel.pullRequests.collectAsState().value) {
            is ViewState.Loading -> LoadingComponent()
            is ViewState.Error -> ErrorDialog(onClick = { launch() })
            is ViewState.Success -> DetailScreen(response.data)
            else -> Unit
        }
    }
}

