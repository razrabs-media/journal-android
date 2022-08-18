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
import ru.razrabs.design.theming.background
import ru.razrabs.design.theming.contrastPrimary
import ru.razrabs.design.theming.secondary
import ru.razrabs.design.theming.styreneBold

@Composable
fun ArticleGroupButton(title: String, onClick: () -> Unit, active: Boolean) {
    val backgroundColor by animateColorAsState(
        targetValue = if (active) secondary() else secondary().copy(
            alpha = 0.6f
        )
    )
    val textColor by animateColorAsState(targetValue = if (active) background() else contrastPrimary())
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        backgroundColor = backgroundColor,
        elevation = zeroElevation()
    ) {
        Text(
            text = title,
            style = styreneBold(color = textColor, size = 12),
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
    ArticleGroupButton(title = payload.title, onClick = {}, active = payload.active)
}