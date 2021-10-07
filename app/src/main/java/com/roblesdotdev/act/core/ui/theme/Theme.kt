package com.roblesdotdev.act.core.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ACTasksTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = lightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
