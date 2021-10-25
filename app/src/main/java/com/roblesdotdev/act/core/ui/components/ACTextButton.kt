package com.roblesdotdev.act.core.ui.components

import android.content.res.Configuration
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme

/**
 * This is a custom version of [TextButton] that ensures the supplied [text] is capitalized.
 */
@Composable
fun ACTextButton(
    text: String,
    onClick: () -> Unit,
) {
    TextButton(onClick) {
        Text(text.toUpperCase(Locale.current))
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun ACTextButtonPreview() {
    ACTasksTheme {
        Surface {
            ACTextButton(
                text = "This is a preview text",
                onClick = {}
            )
        }
    }
}
