package com.roblesdotdev.act.core.ui

import android.content.Context
import androidx.annotation.StringRes

/**
 * This class contains all of the different ways text can be presented to the UI.
 */
sealed class UIText {
    data class StringText(val value: String) : UIText()

    data class ResourceText(@StringRes val value: Int) : UIText()
}

/**
 * Evaluates the value of this [UIText] based on its type.
 *
 * @param[context] if necessary, use this evaluate a string resource.
 */
fun UIText.getString(context: Context): String {
    return when (this) {
        is UIText.StringText -> this.value
        is UIText.ResourceText -> context.getString(this.value)
    }
}
