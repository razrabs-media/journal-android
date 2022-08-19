package ru.razrabs.feature_feed.data

import org.koin.core.annotation.Single
import ru.razrabs.feature_feed.domain.FeedCache
import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.network.models.post.Post

@Single
class FeedCacheImpl: FeedCache {
    override var frontPage: FrontPage? = null

    private val postMap = HashMap<String, Post>()

    override fun savePost(post: Post) {
        postMap[post.uid] = post
    }

    override fun getPost(postUid: String): Post? {
        return postMap[postUid]
    }
}