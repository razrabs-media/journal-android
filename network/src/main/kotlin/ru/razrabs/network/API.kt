package ru.razrabs.network

import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.comments.Comments
import ru.razrabs.network.models.feeds.Feed
import ru.razrabs.network.models.feeds.PostsByFeed
import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.network.models.post.Post
import ru.razrabs.network.models.user.CurrentUser

interface API {
    suspend fun getCurrentFrontPage(): Result<FrontPage, Exception>

    suspend fun getFeeds(): Result<Feed, Exception>

    suspend fun getPostsByFeed(uid: String): Result<PostsByFeed, Exception>

    suspend fun getCurrentUser(): Result<CurrentUser, Exception>

    suspend fun getComments(postUid: String): Result<Comments, Exception>

    suspend fun getPost(postUid: String): Result<Post, Exception>
}