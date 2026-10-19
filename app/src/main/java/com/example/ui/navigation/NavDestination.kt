package com.example.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.FormatListBulleted
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDestination(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
) {
    data object Syllabus : NavDestination(
        route = "syllabus",
        title = "Syllabus",
        selectedIcon = Icons.Filled.FormatListBulleted,
        unselectedIcon = Icons.Outlined.FormatListBulleted,
        testTag = "nav_item_syllabus"
    )

    data object PYQs : NavDestination(
        route = "pyqs",
        title = "PYQs",
        selectedIcon = Icons.Filled.Quiz,
        unselectedIcon = Icons.Outlined.Quiz,
        testTag = "nav_item_pyqs"
    )

    data object Notes : NavDestination(
        route = "notes",
        title = "Notes",
        selectedIcon = Icons.Filled.MenuBook,
        unselectedIcon = Icons.Outlined.MenuBook,
        testTag = "nav_item_notes"
    )

    data object CurrentAffairs : NavDestination(
        route = "current_affairs",
        title = "Current Affairs",
        selectedIcon = Icons.Filled.Article,
        unselectedIcon = Icons.Outlined.Article,
        testTag = "nav_item_current_affairs"
    )

    companion object {
        val items: List<NavDestination>
            get() = listOf(Syllabus, PYQs, Notes, CurrentAffairs)
    }
}
