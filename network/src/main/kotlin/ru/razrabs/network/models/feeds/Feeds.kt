package ru.razrabs.network.models.feeds

import ru.razrabs.graphql.GetFeedsQuery

data class Feed(val data: List<FeedItem>)

data class FeedItem(val uid: String, val name: String)

fun GetFeedsQuery.Data.map() = Feed(data = feeds.map {
    FeedItem(uid = it.uid.toString(), name = it.name)
})