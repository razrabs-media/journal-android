package ru.razrabs.feature_feed.data

import org.koin.core.annotation.Single
import ru.razrabs.feature_feed.domain.FeedNetworkDataSource
import ru.razrabs.network.API

@Single
class FeedDataSourceImpl(private val api: API): FeedNetworkDataSource {
    override fun loadFrontPage() {
        TODO("Not yet implemented")
    }
}