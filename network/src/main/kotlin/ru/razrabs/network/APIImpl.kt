package ru.razrabs.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.Result
import ru.razrabs.graphql.*
import ru.razrabs.graphql.type.CreateCommentInput
import ru.razrabs.network.models.comments.Comments
import ru.razrabs.network.models.comments.map
import ru.razrabs.network.models.feeds.Feed
import ru.razrabs.network.models.feeds.PostsByFeed
import ru.razrabs.network.models.feeds.map
import ru.razrabs.network.models.front_page.FrontPage
import ru.razrabs.network.models.front_page.map
import ru.razrabs.network.models.post.Post
import ru.razrabs.network.models.post.map
import ru.razrabs.network.models.user.CurrentUser
import ru.razrabs.network.models.user.map
import java.util.concurrent.TimeUnit

@Single
class APIImpl : API {

    private var BASE_URL = BuildConfig.API_ENDPOINT

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

    override suspend fun getCurrentUser(): Result<CurrentUser, Exception> {
        val result = apolloClient.query(CurrentUserQuery()).execute()
        val data = result.data?.currentUser
        val profile = data?.profile
        return if (result.errors.isNullOrEmpty() && data != null && profile != null) {
            Ok(data.map(profile))
        } else {
            Err(RuntimeException())
        }
    }

    override suspend fun getComments(postUid: String): Result<Comments, Exception> {
        val result = apolloClient.query(CommentsQuery(postUid = postUid, perPage = 100)).execute()
        val data = result.data?.comments
        return if (result.errors.isNullOrEmpty() && data != null) {
            Ok(data.map())
        } else {
            Err(RuntimeException())
        }
    }

    override suspend fun getPost(postUid: String): Result<Post, Exception> {
        val result = apolloClient.query(GetPostQuery(postUid)).execute()
        val data = result.data
        return if (result.errors.isNullOrEmpty() && data != null) {
            Ok(data.map())
        } else {
            Err(RuntimeException())
        }
    }

    override suspend fun createComment(content: String, postUid: String): Result<Unit, Exception> {
        val result = apolloClient.mutation(SendCommentMutation(CreateCommentInput(content, postUid))).execute()
        val data = result.data
        return if(result.errors.isNullOrEmpty() && data!=null){
            Ok(Unit)
        } else {
            Err(RuntimeException())
        }
    }
}