package com.example.interchallenge

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.interchallenge.presentation.ui.MainActivity
import com.example.interchallenge.presentation.ui.components.core.AppTopBar
import com.example.interchallenge.presentation.ui.theme.InterChallengeTheme
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalFoundationApi
@RunWith(AndroidJUnit4::class)
class AppBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun given_title_should_be_displayed_in_home_app_bar() {

        val title = composeTestRule.activity.getString(R.string.home_title)
        composeTestRule.setContent {
            InterChallengeTheme {
                AppTopBar(title = title, showBackButton = false, navigateBack = {})
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun back_button_should_be_displayed_in_home_app_bar() {
        val title = composeTestRule.activity.getString(R.string.home_title)
        val backButtonDescription =
            composeTestRule.activity.getString(R.string.back_button_description)

        composeTestRule.setContent {
            InterChallengeTheme {
                AppTopBar(title = title, showBackButton = true, navigateBack = {})
            }
        }
        composeTestRule.onNodeWithContentDescription(backButtonDescription).assertIsDisplayed()
    }

    @Test
    fun app_bar_icons_should_be_clickable_in_home_app_bar() {
        val title = composeTestRule.activity.getString(R.string.home_title)
        val backButtonDescription =
            composeTestRule.activity.getString(R.string.back_button_description)
        var testBackButtonClick = false

        composeTestRule.setContent {
            InterChallengeTheme {
                AppTopBar(
                    title = title,
                    showBackButton = true,
                    navigateBack = { testBackButtonClick = true }
                )
            }
        }
        composeTestRule.onNodeWithContentDescription(backButtonDescription).performClick()
        Assert.assertTrue(testBackButtonClick)
    }
}