package ru.razrabs.feature_feed.domain

import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.network.models.post.Post

interface FeedCache {
    var frontPage: FrontPage?
    fun savePost(post: Post)
    fun getPost(postUid: String): Post?
}