package ru.razrabs.feature_comments.data

import org.koin.core.annotation.Single
import ru.razrabs.core.ext.Result
import ru.razrabs.feature_comments.domain.CommentDataSource
import ru.razrabs.network.API
import ru.razrabs.network.models.comments.Comments

@Single
class CommentDataSourceImpl(private val api: API) : CommentDataSource {
    override suspend fun getComments(postUid: String): Result<Comments, Exception> {
        return api.getComments(postUid)
    }

    override suspend fun createComment(postUid: String, content: String): Result<Unit, Exception> {
        return api.createComment(postUid = postUid, content = content)
    }
}