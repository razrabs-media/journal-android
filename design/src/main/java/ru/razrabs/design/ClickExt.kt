package ru.razrabs.design

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.unboundClickable(block: (() -> Unit)): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(bounded = false),
        onClick = { block() }
    )
}

fun Modifier.unboundClickableValueCheck(
    currentValue: Int,
    turnOffValue: Int,
    block: (() -> Unit)
): Modifier = unboundClickableValueCheck(enabled = currentValue == turnOffValue, block = block)

fun Modifier.unboundClickableValueCheck(enabled: Boolean, block: (() -> Unit)): Modifier =
    composed {
        this.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = if (enabled) rememberRipple(bounded = false) else null,
            onClick = { block() }
        )
    }

fun Modifier.clickableWithoutRipple(block: (() -> Unit)): Modifier = composed {
    this.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = { block() }
    )
}