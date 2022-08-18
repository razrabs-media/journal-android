package ru.razrabs.network.models.comments

import ru.razrabs.graphql.CommentsQuery

data class Comments(
    val items: List<Item>,
)

data class Item(
    val uid: String,
    val author: Author,
    val replyingToComment: ReplyingToComment?,
    val content: String,
    val createdAt: String,
)

data class Author(
    val uid: String,
    val avatarUrl: String?,
    val name: String?,
)

data class ReplyingToComment(
    val uid: String,
    val content: String,
)

private fun CommentsQuery.ReplyingToComment.map() =
    ReplyingToComment(uid = uid.toString(), content = content)

private fun CommentsQuery.Author.map() =
    Author(uid = uid.toString(), avatarUrl = avatarUrl, name = publicName)

fun CommentsQuery.Comments.map() = Comments(items.map {
    Item(
        uid = it.uid.toString(),
        author = it.author.map(),
        replyingToComment = it.replyingToComment?.map(),
        content = it.content,
        createdAt = it.createdAt.toString()
    )
})