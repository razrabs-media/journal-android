package ru.razrabs.design.theming

import androidx.compose.ui.graphics.Color

val lightPalette = AppTheme(
    logo = Color(0xFF000000),
    primary = Color(0xFF1D1D21),
    contrastPrimary = Color(0xFFE2E2E8),
    secondary = Color(0xFF1d1d21),
    contrastSecondary = Color(0xFFECEDF1),
    brand = Color(0xFFFF9B25),
    background = Color(0xFFF7F8FC),
    backgroundSecondary = Color(0xFFECEDF1),
    contrastBackgroundSecondary = Color(0xFFECEDF1)
)

val darkPalette = AppTheme(
    logo = Color(0xFFFFFFFF),
    primary = Color(0xFFE2E2E8),
    contrastPrimary = Color(0xFF1F2025),
    secondary = Color(0xFF93949A),
    contrastSecondary = Color(0xFF2B2C2F),
    brand = Color(0xFFC799E3),
    background = Color(0xFF1F2025),
    backgroundSecondary = Color(0xFF2D2E33),
    contrastBackgroundSecondary = Color(0xFF38393D)
)