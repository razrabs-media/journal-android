package ru.razrabs.design.subcomponents.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource

@Composable
fun VectorIcon(
    icon: Int,
    contentDescription: String = "",
    modifier: Modifier = Modifier
) {
    val vector = ImageVector.vectorResource(id = icon)
    val painter = rememberVectorPainter(image = vector)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
    )
}

@Composable
fun VectorIconColorFilter(
    icon: Int,
    contentDescription: String = "",
    color: Color,
    modifier: Modifier = Modifier
) {
    val vector = ImageVector.vectorResource(id = icon)
    val painter = rememberVectorPainter(image = vector)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        colorFilter = ColorFilter.tint(color),
        modifier = modifier
    )
}

@Composable
fun VectorImage(
    icon: Int,
    contentDescription: String = "",
    modifier: Modifier = Modifier
) {
    val vector = ImageVector.vectorResource(id = icon)
    val painter = rememberVectorPainter(image = vector)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun VectorImageColorFilter(
    icon: Int,
    contentDescription: String = "",
    modifier: Modifier = Modifier,
    color: Color,
) {
    val vector = ImageVector.vectorResource(id = icon)
    val painter = rememberVectorPainter(image = vector)
    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier
            .fillMaxWidth(),
        colorFilter = ColorFilter.tint(color),
        contentScale = ContentScale.FillWidth
    )
}