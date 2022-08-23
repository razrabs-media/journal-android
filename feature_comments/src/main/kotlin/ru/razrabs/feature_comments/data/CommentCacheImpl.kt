package ru.razrabs.feature_comments.data

import org.koin.core.annotation.Single
import ru.razrabs.feature_comments.domain.CommentCache
import ru.razrabs.network.models.comments.Comments

@Single
class CommentCacheImpl : CommentCache {

    private val commentMap = hashMapOf<String, Comments>()

    override suspend fun getComments(postUid: String): Comments? {
        return commentMap[postUid]
    }

    override suspend fun saveComments(postUid: String, comments: Comments) {
        commentMap[postUid] = comments
    }
}