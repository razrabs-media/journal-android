package ru.razrabs.feature_comments.data

import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Ok
import ru.razrabs.core.ext.Result
import ru.razrabs.feature_comments.domain.CommentCache
import ru.razrabs.feature_comments.domain.CommentDataSource
import ru.razrabs.feature_comments.domain.CommentRepository
import ru.razrabs.network.models.comments.Comments

@Single
class CommentRepositoryImpl(
    private val commentCache: CommentCache,
    private val commentDataSource: CommentDataSource
) : CommentRepository {
    override suspend fun getComments(postUid: String): Result<Comments, Exception> {
        commentCache.getComments(postUid)?.let {
            return Ok(it)
        } ?: run {
            val result = commentDataSource.getComments(postUid)
            if (result is Ok) {
                commentCache.saveComments(postUid, result.value)
            }
            return result
        }
    }

    override suspend fun createComment(postUid: String, content: String): Result<Unit, Exception> {
        return commentDataSource.createComment(postUid, content)
    }
}