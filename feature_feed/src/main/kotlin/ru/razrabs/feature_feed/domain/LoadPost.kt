package ru.razrabs.feature_feed.domain

import org.koin.core.annotation.Single

@Single
class LoadPost(private val feedRepository: FeedRepository) {
    suspend operator fun invoke(postUid: String) = feedRepository.getPost(postUid)
}