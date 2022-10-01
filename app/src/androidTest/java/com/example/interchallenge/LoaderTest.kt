package com.example.interchallenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.interchallenge.core.constants.LOADING_COMPONENT_TAG
import com.example.interchallenge.core.constants.LOADING_ITEM_TAG
import com.example.interchallenge.presentation.ui.components.core.LoadingComponent
import com.example.interchallenge.presentation.ui.components.core.LoadingItem
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
class LoaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_be_able_to_display_progress_bar_item() {
        composeTestRule.setContent {
            LoadingComponent()
        }
        composeTestRule.onNode(hasTestTag(LOADING_COMPONENT_TAG)).assertIsDisplayed()
    }

    @Test
    fun should_be_able_to_display_progress_bar() {
        composeTestRule.setContent {
            LoadingItem()
        }
        composeTestRule.onNode(hasTestTag(LOADING_ITEM_TAG)).assertIsDisplayed()
    }
}