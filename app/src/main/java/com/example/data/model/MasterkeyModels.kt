package com.example.data.model

data class SyllabusItem(
    val id: String,
    val stage: String, // "PRELIMS", "MAINS"
    val paper: String, // "GS1", "GS2", "GS3", "GS4", "CSAT"
    val subject: String,
    val topicTitle: String,
    val subtopics: List<String>,
    var isCompleted: Boolean = false
)

data class PyqItem(
    val id: String,
    val examStage: String, // "PRELIMS", "MAINS"
    val examYear: Int,
    val paper: String,
    val subject: String,
    val questionText: String,
    val options: Map<String, String>?,
    val correctOption: String?,
    val shortExplanation: String,
    val modelDimensions: List<String> = emptyList()
)

data class NoteItem(
    val id: String,
    val paper: String, // "GS1", "GS2", "GS3", "GS4"
    val subject: String,
    val title: String,
    val summaryExcerpt: String,
    val definitionContext: String,
    val constitutionalArticles: List<String>,
    val keyDimensions: List<String>,
    val wayForward: String,
    val readTimeMinutes: Int = 3
)

data class CurrentAffairsItem(
    val id: String,
    val title: String,
    val sourceName: String,
    val publishedDate: String,
    val syllabusPaper: String,
    val syllabusTopic: String,
    val summaryBullets: List<String>,
    val prelimsFacts: List<String>,
    val mainsTakeaway: String?
)
