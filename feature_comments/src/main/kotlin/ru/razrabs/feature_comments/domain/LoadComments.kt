package ru.razrabs.feature_comments.domain

import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.feature_feed.domain.FeedRepository
import ru.razrabs.network.models.post.Comment

@Single
class LoadComments(private val feedRepository: FeedRepository) {
    suspend operator fun invoke(postUid: String): ru.razrabs.core.ext.Result<List<Comment>, Exception> {
        return when(val result = feedRepository.getPost(postUid)){
            is Ok -> {
                Ok(result.value.comments)
            }
            is Err -> {
                Err(result.error)
            }
        }
    }
}