package ru.razrabs.feature_comments.domain

import ru.razrabs.core.ext.Result
import ru.razrabs.network.models.comments.Comments

interface CommentCache {
    suspend fun getComments(postUid: String): Comments?
    suspend fun saveComments(postUid: String, comments: Comments)
}