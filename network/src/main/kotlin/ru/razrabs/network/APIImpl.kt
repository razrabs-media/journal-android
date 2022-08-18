package ru.razrabs.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import okhttp3.OkHttpClient
import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.Result
import ru.razrabs.graphql.CurrentFrontPageQuery
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

    override suspend fun getCurrentFrontPage(): Result<Unit, Exception> {
        val query = apolloClient.query(CurrentFrontPageQuery())
        val result = query.execute()
        return if (result.errors.isNullOrEmpty() && result.data != null) {
            result.data
            Ok(Unit)
        } else {
            Err(RuntimeException())
        }
    }
}