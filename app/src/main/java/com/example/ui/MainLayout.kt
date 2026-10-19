package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.repository.MasterkeyRepository
import com.example.ui.navigation.NavDestination
import com.example.ui.screens.CurrentAffairsScreen
import com.example.ui.screens.NotesScreen
import com.example.ui.screens.PyqsScreen
import com.example.ui.screens.SyllabusScreen
import com.example.ui.theme.SuccessEmerald

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainLayout(
    repository: MasterkeyRepository = remember { MasterkeyRepository() },
    modifier: Modifier = Modifier
) {
    var currentRoute by rememberSaveable { mutableStateOf(NavDestination.Syllabus.route) }

    val currentDestination = when (currentRoute) {
        NavDestination.PYQs.route -> NavDestination.PYQs
        NavDestination.Notes.route -> NavDestination.Notes
        NavDestination.CurrentAffairs.route -> NavDestination.CurrentAffairs
        else -> NavDestination.Syllabus
    }

    // Handle back button: if on a secondary tab, return to Syllabus
    BackHandler(enabled = currentDestination != NavDestination.Syllabus) {
        currentRoute = NavDestination.Syllabus.route
    }

    // Collect reactive StateFlows from Repository
    val syllabusList by repository.syllabusItems.collectAsState()
    val pyqList by repository.pyqItems.collectAsState()
    val noteList by repository.noteItems.collectAsState()
    val currentAffairsList by repository.currentAffairsItems.collectAsState()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "UPSC MASTERKEY",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.sp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Distraction-Free Preparation",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 10.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                actions = {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                        modifier = Modifier.padding(end = 12.dp)
                    ) {
                        androidx.compose.foundation.layout.Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.CloudDone,
                                contentDescription = "Cloud Connected",
                                tint = SuccessEmerald,
                                modifier = Modifier.size(14.dp)
                            )
                            androidx.compose.foundation.layout.Spacer(modifier = Modifier.size(4.dp))
                            Text(
                                text = "Synced",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 10.sp,
                                color = SuccessEmerald,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.testTag("bottom_nav_bar"),
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 6.dp
            ) {
                NavDestination.items.forEach { destination ->
                    val isSelected = currentDestination.route == destination.route
                    NavigationBarItem(
                        selected = isSelected,
                        onClick = { currentRoute = destination.route },
                        icon = {
                            Icon(
                                imageVector = if (isSelected) destination.selectedIcon else destination.unselectedIcon,
                                contentDescription = destination.title,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                text = destination.title,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onPrimary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                            unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                        ),
                        modifier = Modifier.testTag(destination.testTag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = currentDestination, label = "TabCrossfade") { destination ->
                when (destination) {
                    NavDestination.Syllabus -> SyllabusScreen(
                        syllabusList = syllabusList,
                        onToggleCompletion = { id -> repository.toggleSyllabusCompletion(id) },
                        onNavigateToPyqs = { currentRoute = NavDestination.PYQs.route },
                        onNavigateToNotes = { currentRoute = NavDestination.Notes.route }
                    )
                    NavDestination.PYQs -> PyqsScreen(
                        pyqList = pyqList
                    )
                    NavDestination.Notes -> NotesScreen(
                        noteList = noteList
                    )
                    NavDestination.CurrentAffairs -> CurrentAffairsScreen(
                        currentAffairsList = currentAffairsList
                    )
                }
            }
        }
    }
}
