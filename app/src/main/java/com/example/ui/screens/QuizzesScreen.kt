package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.SuccessEmerald

@Composable
fun QuizzesScreen(
    onNavigateBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onNavigateBackToHome()
    }

    var selectedCategory by remember { mutableStateOf("Daily Diagnostic") }
    val categories = listOf("Daily Diagnostic", "PYQ Bank (2013-25)", "CSAT Drill", "Re-attempt Vault")

    // Interactive Quiz State
    var selectedOptionIndex by remember { mutableIntStateOf(-1) }
    var isSubmitted by remember { mutableStateOf(false) }

    val options = listOf(
        "A. 1 and 2 only",
        "B. 2 only",
        "C. 1 and 3 only",
        "D. 1, 2 and 3"
    )
    val correctIndex = 1 // Option B is correct

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Stats Overview Bar
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("quizzes_stats_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                QuizMetricItem(label = "Accuracy", value = "78%")
                QuizMetricItem(label = "Avg Speed", value = "42s / Q")
                QuizMetricItem(label = "National Rank", value = "Top 4%")
                QuizMetricItem(label = "Streak", value = "42 🔥")
            }
        }

        // Category Filter Chips
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            categories.forEach { category ->
                FilterChip(
                    selected = selectedCategory == category,
                    onClick = { selectedCategory = category },
                    label = { Text(category, fontSize = 12.sp) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primary,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    modifier = Modifier.testTag("filter_${category.replace(" ", "_").lowercase()}")
                )
            }
        }

        // Interactive Live Daily Quiz Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .testTag("interactive_quiz_card"),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Question Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = RoundedCornerShape(6.dp)
                    ) {
                        Text(
                            text = "GS-2: POLITY & CONSTITUTION",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                    Text(
                        text = "Moderate • +2.00 / -0.66",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "Q4. With reference to the Inter-State Council, consider the following statements:",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "1. It is a permanent constitutional body established under Article 263.\n" +
                            "2. The Prime Minister acts as its Chairman.\n" +
                            "3. Its recommendations are legally binding on all states.\n\n" +
                            "Which of the statements given above is/are correct?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )

                // Options List
                options.forEachIndexed { index, text ->
                    val isSelected = selectedOptionIndex == index
                    val isCorrectChoice = isSubmitted && index == correctIndex
                    val isWrongChoice = isSubmitted && isSelected && index != correctIndex

                    val borderColor = when {
                        isCorrectChoice -> SuccessEmerald
                        isWrongChoice -> ErrorRose
                        isSelected -> MaterialTheme.colorScheme.primary
                        else -> MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                    }

                    val containerColor = when {
                        isCorrectChoice -> SuccessEmerald.copy(alpha = 0.12f)
                        isWrongChoice -> ErrorRose.copy(alpha = 0.12f)
                        isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                        else -> MaterialTheme.colorScheme.surface
                    }

                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .border(1.5.dp, borderColor, RoundedCornerShape(10.dp))
                            .clickable(enabled = !isSubmitted) {
                                selectedOptionIndex = index
                            }
                            .testTag("option_$index"),
                        color = containerColor,
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = text,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f)
                            )
                            if (isSubmitted) {
                                if (index == correctIndex) {
                                    Icon(
                                        imageVector = Icons.Filled.Check,
                                        contentDescription = "Correct",
                                        tint = SuccessEmerald,
                                        modifier = Modifier.size(20.dp)
                                    )
                                } else if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Filled.Close,
                                        contentDescription = "Incorrect",
                                        tint = ErrorRose,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    if (!isSubmitted) {
                        Button(
                            onClick = {
                                if (selectedOptionIndex != -1) {
                                    isSubmitted = true
                                }
                            },
                            enabled = selectedOptionIndex != -1,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(46.dp)
                                .testTag("btn_submit_quiz_answer"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Submit Answer", fontWeight = FontWeight.SemiBold)
                        }
                    } else {
                        OutlinedButton(
                            onClick = {
                                isSubmitted = false
                                selectedOptionIndex = -1
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("btn_try_again"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = Icons.Filled.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Reset")
                        }

                        Button(
                            onClick = {
                                isSubmitted = false
                                selectedOptionIndex = -1
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(46.dp)
                                .testTag("btn_next_question"),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Next Question")
                        }
                    }
                }

                // Instant Concept Deconstruction Box (Revealed upon submit)
                AnimatedVisibility(visible = isSubmitted) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Lightbulb,
                                contentDescription = "Concept",
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "CONCEPT DECONSTRUCTION",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }

                        Text(
                            text = "• Statement 1 is INCORRECT: Article 263 allows the President to establish the Inter-State Council, but it is NOT a permanent constitutional body (it is set up by presidential order).\n" +
                                    "• Statement 2 is CORRECT: The Prime Minister is the Chairman of the Council, with Chief Ministers and 6 Cabinet Ministers as members.\n" +
                                    "• Statement 3 is INCORRECT: Recommendations are purely advisory, not legally binding.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "🔗 Related PYQs: 2013 Q18, 2021 Q44",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Why Asked: 28th Standing Committee",
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Section: Subject-wise Mock Tests
        Text(
            text = "📚 Sectional Practice Quizzes",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        SectionalQuizCard(
            title = "Indian Polity: Parliament & Judiciary",
            details = "15 MCQs • 20 Mins • Negative Marking Active",
            tag = "GS-2",
            difficulty = "Moderate",
            onStart = {}
        )

        SectionalQuizCard(
            title = "Modern Indian History & Freedom Struggle",
            details = "20 MCQs • 25 Mins • 2013-2024 PYQs Integrated",
            tag = "GS-1",
            difficulty = "Hard",
            onStart = {}
        )

        SectionalQuizCard(
            title = "Environment & Biodiversity Treaties",
            details = "15 MCQs • 18 Mins • COP-30 & Ramsar Sites",
            tag = "GS-3",
            difficulty = "Moderate",
            onStart = {}
        )
    }
}

@Composable
private fun QuizMetricItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun SectionalQuizCard(
    title: String,
    details: String,
    tag: String,
    difficulty: String,
    onStart: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("sectional_quiz_${tag.lowercase()}"),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = tag,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                    Surface(
                        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = difficulty,
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = details,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            IconButton(
                onClick = onStart,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Start Quiz",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}
