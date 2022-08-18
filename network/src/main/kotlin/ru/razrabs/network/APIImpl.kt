package ru.razrabs.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.Result
import ru.razrabs.graphql.CurrentFrontPageQuery
import ru.razrabs.graphql.GetFeedsQuery
import ru.razrabs.graphql.PostsByFeedQuery
import ru.razrabs.network.models.feeds.Feed
import ru.razrabs.network.models.feeds.PostsByFeed
import ru.razrabs.network.models.feeds.map
import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.network.models.front_page.map
import java.util.concurrent.TimeUnit

@Single
class APIImpl : API {

    private var BASE_URL = "https://api.razrabs.ru/gql"

    private val httpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .callTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private val apolloClient = ApolloClient.Builder()
        .serverUrl(BASE_URL)
        .okHttpClient(httpClient)
        .build()

    override suspend fun getCurrentFrontPage(): Result<FrontPage, Exception> {
        val query = apolloClient.query(CurrentFrontPageQuery())
        val result = query.execute()
        val data = result.data?.currentFrontPage?.frontPageResultPartial
        return if (result.errors.isNullOrEmpty() && data != null) {
            Ok(data.map())
        } else {
            Err(RuntimeException())
        }
    }

    override suspend fun getFeeds(): Result<Feed, Exception> {
        val result = apolloClient.query(GetFeedsQuery()).execute()
        val data = result.data
        return if (result.errors.isNullOrEmpty() && data != null) {
            Ok(data.map())
        } else {
            Err(RuntimeException())
        }
    }

    override suspend fun getPostsByFeed(uid: String): Result<PostsByFeed, Exception> {
        val result = apolloClient.query(PostsByFeedQuery(uid = uid)).execute()
        val data = result.data?.postsByFeed
        return if (result.errors.isNullOrEmpty() && data != null) {
            Ok(data.map())
        } else {
            Err(RuntimeException())
        }
    }
}