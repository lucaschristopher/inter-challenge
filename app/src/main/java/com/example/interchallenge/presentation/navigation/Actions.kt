package com.example.interchallenge.presentation.navigation

import androidx.navigation.NavHostController

class Actions(navHostController: NavHostController) {

    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }

    val openPullRequestDetail: (String, String) -> Unit = { userArg, repoArg ->
        navHostController.navigate(
            Route.Detail.createRoute(userArg, repoArg)
        )
    }
}