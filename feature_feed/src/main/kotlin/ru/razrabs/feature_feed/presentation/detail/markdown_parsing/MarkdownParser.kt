package ru.razrabs.feature_feed.presentation.detail.markdown_parsing

import org.koin.core.annotation.Single

@Single
class MarkdownParser {

    fun parse(content: String): List<MarkdownItem> {
        val articleElements = content.split("\n")
        var parsingCode = false
        val codeElements = arrayListOf<String>()
        var codeLanguage = ""
        val items = articleElements.mapIndexedNotNull { index, element ->
            val nextItem = articleElements.getOrNull(index + 1)
            when {
                element.trim().startsWith(QUOTATION_PREFIX) -> {
                    val level = element.filter { it == '>' }.length
                    val sb = StringBuilder()
                    for (i in 0 until level){
                        sb.append("\t\t\t\t${element.drop(level).trim()}")
                    }
                    MarkdownItem.Quotation(text = "\t\t\t\t${element.drop(2)}")
                }
                nextItem != null && (nextItem.startsWith(FIRST_LEVEL_BLOCK) || nextItem.startsWith(
                    SECOND_LEVEL_BLOCK
                )) -> {
                    MarkdownItem.Header(element)
                }
                element.startsWith(LIST_ITEM) -> {
                    val text = parseItemText(element)
                    text.copy(elements = arrayListOf<TextElement>().apply {
                        add(TextElement.UsualText(" > "))
                        addAll(text.elements)
                    })
                }
                element.startsWith(SEPARATOR_PREFIX) -> MarkdownItem.Separator
                parsingCode -> {
                    if (element.trim().endsWith(CODE_PREFIX)) {
                        parsingCode = false
                        codeElements.add(element.trim().dropLast(3))
                        val localElements = ArrayList(codeElements)
                        codeElements.clear()
                        MarkdownItem.Code(language = codeLanguage, elements = localElements)
                    } else {
                        codeElements.add(element)
                        null
                    }
                }
                element.startsWith(CODE_PREFIX) -> {
                    parsingCode = true
                    codeLanguage = element.removePrefix(CODE_PREFIX)
                    null
                }
                element.startsWith(HEADER_PREFIX) || element.startsWith(ALTERNATIVE_HEADER_PREFIX) -> {
                    MarkdownItem.Header(
                        element.removePrefix(HEADER_PREFIX).removePrefix(ALTERNATIVE_HEADER_PREFIX)
                            .drop(1)
                    )
                }
                element.isEmpty() -> null
                element.startsWith(IMAGE_PREFIX) -> {
                    val elements = element.removePrefix(IMAGE_PREFIX).split("[", "]", "(", ")")
                        .filter { it.isNotEmpty() }
                    MarkdownItem.Image(contentDescription = elements.getOrElse(0) { "" },
                        path = elements.getOrElse(1) { "" })
                }
                else -> parseItemText(element)
            }
        }
        return items
    }

    private fun parseItemText(element: String): MarkdownItem.Text {
        val elements = arrayListOf<TextElement>()
        var skipNext = false
        var parsingName = false
        var parsingLink = false
        var parsingTooltip = false
        val tooltipCache = StringBuilder()
        val nameCache = StringBuilder()
        val linkCache = StringBuilder()
        val usualTextCache = StringBuilder()
        element.forEachIndexed { index, it ->
            if (skipNext) {
                skipNext = false
            } else {
                if (parsingLink) {
                    if (it == ')') {
                        elements.add(
                            TextElement.Link(
                                text = nameCache.toString(), link = linkCache.toString()
                            )
                        )
                        parsingLink = false
                        nameCache.clear()
                        linkCache.clear()
                    } else {
                        linkCache.append(it)
                    }
                } else if (parsingName) {
                    if (it == ']') {
                        parsingName = false
                        parsingLink = true
                        skipNext = true
                    } else {
                        nameCache.append(it)
                    }
                } else if (it == '[') {
                    elements.add(TextElement.UsualText(usualTextCache.toString()))
                    usualTextCache.clear()
                    parsingName = true
                } else if (it == '`') {
                    if (parsingTooltip) {
                        elements.add(TextElement.Tooltip(tooltipCache.toString()))
                        tooltipCache.clear()
                        parsingTooltip = false
                    } else {
                        elements.add(TextElement.UsualText(usualTextCache.toString()))
                        usualTextCache.clear()
                        parsingTooltip = true
                    }
                } else if (parsingTooltip) {
                    tooltipCache.append(it)
                } else if (index == element.lastIndex) {
                    elements.add(TextElement.UsualText(usualTextCache.toString()))
                } else {
                    usualTextCache.append(it)
                }
            }
        }
        return MarkdownItem.Text(elements)
    }

    companion object {
        private const val LIST_ITEM = "+ "
        private const val HEADER_PREFIX = "##"
        private const val ALTERNATIVE_HEADER_PREFIX = "\n##"
        private const val IMAGE_PREFIX = "!"
        private const val CODE_PREFIX = "```"
        private const val SEPARATOR_PREFIX = "--------"
        private const val FIRST_LEVEL_BLOCK = "========================="
        private const val SECOND_LEVEL_BLOCK = "--------------------------"
        private const val QUOTATION_PREFIX = ">"
    }
}