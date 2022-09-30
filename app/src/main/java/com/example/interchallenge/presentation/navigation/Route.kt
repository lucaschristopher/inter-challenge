package com.example.interchallenge.presentation.navigation

import com.example.interchallenge.core.constants.DETAIL_SCREEN
import com.example.interchallenge.core.constants.HOME_SCREEN
import com.example.interchallenge.core.constants.REPO_ARG
import com.example.interchallenge.core.constants.USER_ARG

sealed class Route(val route: String) {
    object Home : Route(HOME_SCREEN)
    object Detail : Route("$DETAIL_SCREEN/{$USER_ARG}/{$REPO_ARG}") {
        fun createRoute(userArg: String, repoArg: String) = "$DETAIL_SCREEN/$userArg/$repoArg"
    }
}
