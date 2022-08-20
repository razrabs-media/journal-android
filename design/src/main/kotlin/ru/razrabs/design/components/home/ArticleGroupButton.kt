package ru.razrabs.design.components.home

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.subcomponents.common.zeroElevation
import ru.razrabs.design.theming.*

@Composable
fun ArticleGroupButton(title: String, onClick: () -> Unit, active: Boolean, clickable: Boolean) {
    val backgroundColor by animateColorAsState(
        targetValue = if (active) secondary() else backgroundSecondary()
    )
    val textColor by animateColorAsState(targetValue = if (active) background() else secondary())
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        backgroundColor = backgroundColor,
        elevation = zeroElevation(),
        active = clickable
    ) {
        Text(
            text = title,
            style = styreneBold(color = textColor, size = 12, letterSpacing = 1),
            modifier = Modifier.padding(8.dp)
        )
    }
}

data class ArticleGroupButtonPayload(val title: String, val active: Boolean)

class ArticleGroupButtonPayloadProvider :
    CollectionPreviewParameterProvider<ArticleGroupButtonPayload>(
        listOf(
            ArticleGroupButtonPayload("ВСЕ ПОДРЯД", false),
            ArticleGroupButtonPayload("ОТ РЕДАКЦИИ", false),
            ArticleGroupButtonPayload("МНЕНИЯ", false),
            ArticleGroupButtonPayload("ВСЕ ПОДРЯД", true),
            ArticleGroupButtonPayload("ОТ РЕДАКЦИИ", true),
            ArticleGroupButtonPayload("МНЕНИЯ", true),
        )
    )

@Preview
@Composable
fun PreviewArticleGroupButton(@PreviewParameter(ArticleGroupButtonPayloadProvider::class) payload: ArticleGroupButtonPayload) {
    ArticleGroupButton(
        title = payload.title,
        onClick = {},
        active = payload.active,
        clickable = true
    )
}