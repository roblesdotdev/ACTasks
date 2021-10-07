@file:Suppress("MagicNumber")
package com.roblesdotdev.act.ui.theme

import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

private val PrimaryBlue = Color(0xFF111C27)
private val SecondaryOrange = Color(0xFFF49D37)
private val LightGray = Color(0xFFF4F4F8)
private val DarkGray = Color(0xFF282828)

val lightColorPalette = lightColors(
    primary = PrimaryBlue,
    onPrimary = Color.White,
    secondary = SecondaryOrange,
    onSecondary = Color.White,
    background = LightGray,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = PrimaryBlue
)
