package ru.razrabs.network

import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.feeds.Feed
import ru.razrabs.network.models.feeds.PostsByFeed
import ru.razrabs.network.models.front_page.FrontPage

interface API {
    suspend fun getCurrentFrontPage(): Result<FrontPage, Exception>

    suspend fun getFeeds(): Result<Feed, Exception>

    suspend fun getPostsByFeed(uid: String): Result<PostsByFeed, Exception>
}