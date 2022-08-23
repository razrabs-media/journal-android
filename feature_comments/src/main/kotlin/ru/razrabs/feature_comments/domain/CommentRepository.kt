package ru.razrabs.feature_comments.domain

import ru.razrabs.network.models.comments.Comments
import ru.razrabs.core.ext.Result

interface CommentRepository {
    suspend fun getComments(postUid: String): Result<Comments, Exception>
    suspend fun createPost(postUid: String, content: String): Result<Unit, Exception>
}