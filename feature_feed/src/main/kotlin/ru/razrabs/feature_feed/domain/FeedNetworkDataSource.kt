package ru.razrabs.feature_feed.domain

import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.post.Post

interface FeedNetworkDataSource {
    suspend fun loadFrontPage(): Result<FrontPage, Exception>

    suspend fun loadPost(postUid: String): Result<Post, Exception>
}