package ru.razrabs.feature_comments.domain

import org.koin.core.annotation.Single

@Single
class CreateComment(private val commentRepository: CommentRepository) {
    suspend operator fun invoke(text: String, postId: String) =
        commentRepository.createComment(postUid = postId, content = text)
}