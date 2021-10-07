package com.roblesdotdev.act.core.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.roblesdotdev.act.R

private val HeeboExtraBold = FontFamily(Font(R.font.heebo_extrabold))
private val HeeboBold = FontFamily(Font(R.font.heebo_bold))
private val HeeboLight = FontFamily(Font(R.font.heebo_light))
private val HeeboMedium = FontFamily(Font(R.font.heebo_medium))
private val HeeboRegular = FontFamily(Font(R.font.heebo_regular))

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = HeeboExtraBold,
        fontSize = 40.sp
    ),
    h2 = TextStyle(
        fontFamily = HeeboExtraBold,
        fontSize = 36.sp
    ),
    h3 = TextStyle(
        fontFamily = HeeboMedium,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = HeeboMedium,
        fontSize = 16.sp
    ),
    body1 = TextStyle(
        fontFamily = HeeboLight,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = HeeboRegular,
        fontSize = 12.sp
    ),
    button = TextStyle(
        fontFamily = HeeboBold,
        fontSize = 14.sp
    )
)
