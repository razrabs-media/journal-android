package ru.razrabs.feature_comments.domain

import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.comments.Comments

interface CommentDataSource {
    suspend fun getComments(postUid: String): Result<Comments, Exception>
    suspend fun createPost(postUid: String, content: String): Result<Unit, Exception>
}