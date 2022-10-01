package com.example.interchallenge

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.interchallenge.core.util.error.mapToCustomError
import com.example.interchallenge.core.util.preview.repositoryUiModelMockPreview
import com.example.interchallenge.presentation.ui.MainActivity
import com.example.interchallenge.presentation.ui.fragments.home.HomeFragment
import com.example.interchallenge.presentation.ui.fragments.home.HomeScreen
import com.example.interchallenge.presentation.ui.theme.InterChallengeTheme
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun app_bar_should_be_displayed_in_home_fragment() {
        val title = composeTestRule.activity.getString(R.string.home_title)
        composeTestRule.setContent {
            InterChallengeTheme {
                HomeFragment(
                    openPullRequestDetail = { _, _ -> }
                )
            }
        }
        composeTestRule.onNodeWithText(title).assertIsDisplayed()
    }

    @Test
    fun repo_should_be_clickable() {
        var isRepoClicked = false
        composeTestRule.setContent {
            InterChallengeTheme {
                HomeFragment(
                    openPullRequestDetail = { _, _ ->
                        isRepoClicked = true
                    }
                )
            }
        }
        composeTestRule.onNodeWithText("LeetCodeAnimation").performClick()
        Assert.assertTrue(isRepoClicked)
    }

    @Test
    fun should_show_error_dialog_incase_of_error_state() {
        val dialogTitle = composeTestRule.activity.getString(R.string.error_dialog_title)

        val dataFlow = flowOf(
            PagingData.from(
                listOf(repositoryUiModelMockPreview)
            )
        ).mapToCustomError()
        composeTestRule.setContent {
            InterChallengeTheme {
                HomeScreen(
                    javaRepositories = dataFlow.collectAsLazyPagingItems(),
                    openPullRequestDetail = { _, _ -> }
                )
            }
        }
        composeTestRule.onNodeWithText(dialogTitle).assertIsDisplayed()
    }
}