package ru.razrabs.feature_comments.domain

import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Err
import ru.razrabs.core.ext.Ok
import ru.razrabs.feature_feed.domain.FeedRepository
import ru.razrabs.network.models.comments.Comments
import ru.razrabs.network.models.post.Comment

@Single
class LoadComments(private val commentRepository: CommentRepository) {
    suspend operator fun invoke(postUid: String): ru.razrabs.core.ext.Result<Comments, Exception> {
        return when (val result = commentRepository.getComments(postUid)) {
            is Ok -> {
                Ok(result.value)
            }
            is Err -> {
                Err(result.error)
            }
        }
    }
}