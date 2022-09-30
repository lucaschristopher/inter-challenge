package com.example.interchallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.interchallenge.core.constants.EMPTY_STRING
import com.example.interchallenge.core.constants.REPO_ARG
import com.example.interchallenge.core.constants.USER_ARG
import com.example.interchallenge.presentation.ui.fragments.detail.DetailFragment
import com.example.interchallenge.presentation.ui.fragments.home.HomeFragment

@Composable
fun NavGraph(navHostController: NavHostController) {
    val actions = remember(navHostController) { Actions(navHostController) }

    NavHost(
        navController = navHostController,
        startDestination = Route.Home.route
    ) {
        composable(route = Route.Home.route) {
            HomeFragment(
                openPullRequestDetail = actions.openPullRequestDetail
            )
        }
        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(USER_ARG) { type = NavType.StringType },
                navArgument(REPO_ARG) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val userArg = backStackEntry.arguments?.getString(USER_ARG) ?: EMPTY_STRING
            val repoArg = backStackEntry.arguments?.getString(REPO_ARG) ?: EMPTY_STRING

            DetailFragment(
                navigateBack = actions.navigateBack,
                user = userArg,
                repo = repoArg
            )
        }
    }
}