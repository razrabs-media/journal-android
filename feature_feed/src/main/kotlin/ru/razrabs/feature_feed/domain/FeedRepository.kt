package ru.razrabs.feature_feed.domain

import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.post.Post

interface FeedRepository {
    suspend fun getFrontPage(force: Boolean): Result<FrontPage, Exception>

    suspend fun getPost(postUid: String): Result<Post, Exception>
}