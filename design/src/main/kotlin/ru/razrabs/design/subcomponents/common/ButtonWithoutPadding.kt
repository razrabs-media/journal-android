package ru.razrabs.design.subcomponents.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.razrabs.design.theming.background

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonWithoutPadding(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape,
    backgroundColor: Color = background(),
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    borderStroke: BorderStroke? = null,
    active: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumTouchTargetEnforcement provides false,
    ) {
        Button(
            onClick = { onClick() }, shape = shape, colors = ButtonDefaults.buttonColors(
                backgroundColor = backgroundColor,
                disabledBackgroundColor = backgroundColor
            ), modifier = modifier.wrapContentSize(),
            elevation = elevation,
            contentPadding = PaddingValues(0.dp),
            border = borderStroke,
            enabled = active
        ) {
            content()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ButtonWithoutPaddingWithGradient(
    modifier: Modifier = Modifier,
    shape: CornerBasedShape,
    gradientColorList: List<Color>,
    elevation: ButtonElevation = ButtonDefaults.elevation(),
    onClick: () -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    CompositionLocalProvider(
        LocalMinimumTouchTargetEnforcement provides false,
    ) {
        Button(
            onClick = { onClick() }, shape = shape, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Transparent
            ), modifier = modifier,
            elevation = elevation,
            contentPadding = PaddingValues(0.dp)
        ) {
            val gradient = Brush.verticalGradient(gradientColorList)
            Box(
                modifier = Modifier
                    .background(gradient)
                    .then(modifier),
                contentAlignment = Alignment.Center,
            ) {
                content()
            }
        }
    }
}

@Composable
fun zeroElevation() = ButtonDefaults.elevation(
    defaultElevation = 0.dp,
    pressedElevation = 0.dp,
    hoveredElevation = 0.dp,
    focusedElevation = 0.dp,
    disabledElevation = 0.dp
)