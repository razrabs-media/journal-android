package ru.razrabs.design.components.feed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.subcomponents.common.zeroElevation
import ru.razrabs.design.theming.logo
import ru.razrabs.design.theming.secondary
import ru.razrabs.design.theming.styreneBold

@Composable
fun BigArticle(
    modifier: Modifier = Modifier,
    title: String,
    url: String,
    date: String,
    onClick: () -> Unit
) {
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        elevation = zeroElevation(),
    ) {
        BigArticleContent(title = title, url = url, date = date)
    }
}

@Composable
fun BigArticleContent(
    modifier: Modifier = Modifier,
    title: String,
    url: String,
    date: String,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 4.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .memoryCacheKey(url)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .diskCachePolicy(CachePolicy.ENABLED)
                .diskCacheKey(url)
                .scale(Scale.FILL)
                .build(),
            contentDescription = title,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        VerticalSpacer(height = 8)
        Text(text = title.uppercase(), style = styreneBold(color = logo(), size = 16, letterSpacing = 0))
        VerticalSpacer(height = 4)
        Text(
            text = date,
            style = styreneBold(color = secondary(), size = 14, letterSpacing = 1)
        )
        VerticalSpacer(height = 8)
    }
}