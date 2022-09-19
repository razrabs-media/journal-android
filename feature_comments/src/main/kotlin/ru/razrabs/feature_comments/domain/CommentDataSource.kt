package ru.razrabs.feature_comments.domain

import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.comments.Comments

interface CommentDataSource {
    suspend fun getComments(postUid: String): Result<Comments, Exception>
    suspend fun createComment(postUid: String, content: String): Result<Unit, Exception>
}