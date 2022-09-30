package com.example.interchallenge.core.constants

import kotlin.time.Duration.Companion.hours

// Dispatchers
const val DEFAULT_SCOPE = "DefaultScope"
const val MAIN_DISPATCHER = "MainDispatcher"
const val IO_DISPATCHER = "IODispatcher"
const val DEFAULT_DISPATCHER = "DefaultDispatcher"

// Messages
const val DEFAULT_ERROR = "Ocorreu um erro, por favor, tente mais tarde."

// Screens
const val HOME_SCREEN = "home"
const val DETAIL_SCREEN = "detail"
const val ARG_MATCH = "match"

// Utils
const val EMPTY_STRING = ""
const val SPACE_STRING = " "

const val INITIAL_PAGE: Int = 1
const val ONE: Long = 1
const val PAGE_SIZE: Int = 20
const val DEFAULT_ID: Long = 0
const val EMPTY_QUANTITY: Long = 0
const val DEFAULT_TIME_VALUE: Long = 60
const val FIVE_VALUE: Long = 5
const val DESCRIPTION_MAX_LINE: Int = 3
const val TITLE_MAX_LINE: Int = 1

// Search
const val QUERY_PARAM = "language:Java"
const val SORT_PARAM = "stars"

val MAX_CACHE_CONTROL_TIME = ONE.hours.inWholeSeconds.toInt()