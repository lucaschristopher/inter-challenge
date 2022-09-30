package com.example.interchallenge.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.interchallenge.presentation.ui.fragments.detail.DetailFragment
import com.example.interchallenge.presentation.ui.fragments.home.HomeFragment

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Route.Home.route
    ) {
        composable(route = Route.Home.route) {
            HomeFragment(navHostController = navHostController)
        }
        composable(Route.Detail.route) {
            DetailFragment()
        }
    }
}