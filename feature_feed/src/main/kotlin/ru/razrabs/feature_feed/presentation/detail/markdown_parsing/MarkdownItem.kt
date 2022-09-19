package ru.razrabs.feature_feed.presentation.detail.markdown_parsing

sealed class MarkdownItem {
    data class Title(val text: String) : MarkdownItem()
    object Separator : MarkdownItem()
    data class Header(val text: String) : MarkdownItem()
    data class Code(val language: String, val elements: List<String>) : MarkdownItem()
    data class Image(val contentDescription: String, val path: String) : MarkdownItem()
    data class Text(val elements: List<TextElement>) : MarkdownItem()
    data class Quotation(val text: String) : MarkdownItem()
    data class YouTubeVideo(val url: String) : MarkdownItem()
}

sealed class TextElement {
    data class UsualText(val text: String) : TextElement()
    data class Tooltip(val text: String) : TextElement()
    data class Link(val text: String, val link: String) : TextElement()
}
