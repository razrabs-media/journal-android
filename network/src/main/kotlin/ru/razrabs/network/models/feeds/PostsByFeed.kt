package ru.razrabs.network.models.feeds

import ru.razrabs.graphql.PostsByFeedQuery


data class PostsByFeed(
    val items: List<Item>,
)

data class Item(
    val uid: String,
    val title: String,
    val previewUrl: String?,
    val createdAt: String,
)

fun PostsByFeedQuery.PostsByFeed.map() = PostsByFeed(items = this.items.map {
    Item(
        uid = it.uid.toString(),
        title = it.title,
        previewUrl = it.previewUrl,
        createdAt = it.createdAt.toString()
    )
})