package ru.razrabs.network.models.front_page

data class FrontPageContentItem(
    val uid: String,
    val post: FrontPagePost
)

data class FrontPagePost(
    val uid: String,
    val readingTime: Int?,
    val previewUrl: String?,
    val title: String,
    val content: String,
    val description: String,
    val createdAt: String
)