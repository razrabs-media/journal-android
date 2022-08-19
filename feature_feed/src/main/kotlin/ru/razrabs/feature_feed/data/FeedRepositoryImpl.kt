package ru.razrabs.feature_feed.data

import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.Result
import ru.razrabs.feature_feed.domain.FeedCache
import ru.razrabs.feature_feed.domain.FeedNetworkDataSource
import ru.razrabs.feature_feed.domain.FeedRepository
import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.network.models.post.Post
import java.security.PrivateKey

@Single
class FeedRepositoryImpl(
    private val feedNetworkDataSource: FeedNetworkDataSource,
    private val feedCache: FeedCache
) : FeedRepository {

    /**
     * Возвращает главную страницу.
     * @param force - если true, то всегда вернет новую. Если false, то может вернуть из кэша.
     */
    override suspend fun getFrontPage(force: Boolean): Result<FrontPage, Exception> {
        return if (force) {
            getNewFrontPage()
        } else {
            val cached = feedCache.frontPage
            if (cached != null) {
                Ok(cached)
            } else {
                getNewFrontPage()
            }
        }
    }

    override suspend fun getPost(postUid: String): Result<Post, Exception> {
        val cached = feedCache.getPost(postUid)
        return if (cached != null) {
            Ok(cached)
        } else {
            getNewPost(postUid)
        }
    }

    /**
     * Запрашивает с сервера главную страницу и сохраняет в кэш в случае успеха.
     */
    private suspend fun getNewFrontPage(): Result<FrontPage, Exception> {
        val data = feedNetworkDataSource.loadFrontPage()
        if (data is Ok) {
            feedCache.frontPage = data.value
        }
        return data
    }

    /**
     * Запрашивает с сервера пост и сохраняет в кэш в случае успеха.
     * @param postUid - UUID поста.
     */
    private suspend fun getNewPost(postUid: String): Result<Post, Exception> {
        val data = feedNetworkDataSource.loadPost(postUid)
        if (data is Ok) {
            feedCache.savePost(data.value)
        }
        return data
    }
}