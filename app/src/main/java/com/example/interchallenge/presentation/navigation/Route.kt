package com.example.interchallenge.presentation.navigation

import com.example.interchallenge.core.constants.DETAIL_SCREEN
import com.example.interchallenge.core.constants.HOME_SCREEN

sealed class Route(val route: String) {
    object Home : Route(HOME_SCREEN)
    object Detail : Route(DETAIL_SCREEN)
}
