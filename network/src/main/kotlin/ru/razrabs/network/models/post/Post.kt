package ru.razrabs.network.models.post

import ru.razrabs.graphql.GetPostQuery


data class Post(
    val uid: String,
    val title: String,
    val description: String,
    val previewUrl: String?,
    val content: String,
    val tags: List<Tag>?,
    val githubAuthor: GithubAuthor?,
    val createdAt: Any,
    val comments: List<Comment>?,
)

data class Tag(
    val name: String,
)

data class GithubAuthor(
    val uid: String,
    val name: String?,
    val usernameUrl: String,
    val avatarUrl: String?,
)

data class Comment(
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

private fun GetPostQuery.Tag.map() = Tag(name = name)

private fun GetPostQuery.GithubAuthor.map() = GithubAuthor(
    uid = uid.toString(),
    name = name,
    usernameUrl = usernameUrl,
    avatarUrl = avatarUrl
)

private fun GetPostQuery.ReplyingToComment.map() =
    ReplyingToComment(uid = uid.toString(), content = content)

private fun GetPostQuery.Author.map() =
    Author(uid = uid.toString(), avatarUrl = avatarUrl, name = publicName)

private fun GetPostQuery.Comment.map() = Comment(
    uid = uid.toString(),
    author = author.map(),
    replyingToComment = replyingToComment?.map(),
    content = content,
    createdAt = createdAt.toString()
)

fun GetPostQuery.Data.map() = Post(
    uid = post.uid.toString(),
    title = post.title,
    description = post.description,
    previewUrl = post.previewUrl,
    content = post.content,
    tags = post.tags?.map {
        it.map()
    },
    githubAuthor = post.githubAuthor?.map(),
    createdAt = post.createdAt.toString(),
    comments = post.comments?.map { it.map() }
)