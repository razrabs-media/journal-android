package ru.razrabs.design.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable

@Composable
fun isDarkTheme() = isSystemInDarkTheme()
//fun isDarkTheme() = true

@Composable
fun palette() = if (isDarkTheme()) darkPalette else lightPalette

@Composable
fun logo() = palette().logo

@Composable
fun primary() = palette().primary

@Composable
fun contrastPrimary() = palette().contrastPrimary

@Composable
fun secondary() = palette().secondary

@Composable
fun secondary60() = palette().secondary.copy(alpha = 0.6f)

@Composable
fun contrastSecondary() = palette().contrastSecondary

@Composable
fun brand() = palette().brand

@Composable
fun background() = palette().background

@Composable
fun backgroundSecondary() = palette().backgroundSecondary

@Composable
fun contrastBackgroundSecondary() = palette().contrastBackgroundSecondary