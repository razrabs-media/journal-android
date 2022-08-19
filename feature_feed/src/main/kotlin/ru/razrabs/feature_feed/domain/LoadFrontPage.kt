package ru.razrabs.feature_feed.domain

import org.koin.core.annotation.Single

@Single
class LoadFrontPage(private val feedRepository: FeedRepository) {
    suspend operator fun invoke(force: Boolean) = feedRepository.getFrontPage(force)
}