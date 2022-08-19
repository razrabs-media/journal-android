package ru.razrabs.feature_feed.data

import org.koin.core.annotation.Single
import ru.razrabs.feature_feed.domain.FeedNetworkDataSource

@Single
class FeedRepositoryImpl(private val feedNetworkDataSource: FeedNetworkDataSource) {
}