package ru.razrabs.feature_feed.data

import org.koin.core.annotation.Single
import ru.razrabs.feature_feed.domain.FeedNetworkDataSource
import ru.razrabs.network.API
import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.post.Post

@Single
class FeedDataSourceImpl(private val api: API) : FeedNetworkDataSource {
    override suspend fun loadFrontPage(): Result<FrontPage, Exception> = api.getCurrentFrontPage()
    override suspend fun loadPost(postUid: String): Result<Post, Exception> = api.getPost(postUid)
}