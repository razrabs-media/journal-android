package ru.razrabs.network.models.front_page

import ru.razrabs.graphql.fragment.FrontPageResultPartial

data class FrontPage(
    val uid: String,
    val publicationDate: String,
    val content: List<FrontPageContentItem>
)

fun FrontPageResultPartial.map() = FrontPage(
    uid = uid.toString(),
    publicationDate = publicationDate?.toString() ?: "null",
    content = content.map {
        FrontPageContentItem(
            uid = it.uid.toString(),
            post = with(it.post) {
                FrontPagePost(
                    uid = uid.toString(),
                    readingTime = readingTime,
                    previewUrl = previewUrl,
                    title = title,
                    content = content,
                    description = description,
                    createdAt = createdAt.toString()
                )
            }
        )
    }
)
