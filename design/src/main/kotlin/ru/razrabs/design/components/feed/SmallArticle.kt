package ru.razrabs.design.components.feed

import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Scale
import ru.razrabs.design.HorizontalSpacer
import ru.razrabs.design.VerticalSpacer
import ru.razrabs.design.subcomponents.common.ButtonWithoutPadding
import ru.razrabs.design.subcomponents.common.cornerRadius0
import ru.razrabs.design.subcomponents.common.zeroElevation
import ru.razrabs.design.theming.logo
import ru.razrabs.design.theming.secondary
import ru.razrabs.design.theming.styreneBold

@Composable
fun SmallArticle(title: String, url: String, date: String, onClick: () -> Unit) {
    ButtonWithoutPadding(
        shape = cornerRadius0,
        onClick = onClick,
        elevation = zeroElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.Top,
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
                        .fillMaxSize(fraction = 0.3f)
                        .padding(vertical = 8.dp)
                )
                HorizontalSpacer(width = 4)
                Column(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, top = 2.dp, bottom = 4.dp)
                ) {
                    Text(text = title, style = styreneBold(color = logo(), size = 16, letterSpacing = 0))
                    VerticalSpacer(height = 4)
                    Text(text = date, style = styreneBold(color = secondary(), size = 10, letterSpacing = 1))
                }
            }
        }
    }
}