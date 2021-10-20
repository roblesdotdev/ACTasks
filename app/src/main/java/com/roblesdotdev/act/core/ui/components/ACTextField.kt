package com.roblesdotdev.act.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.RelocationRequester
import androidx.compose.ui.layout.relocationRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val MILLI_SECONDS_DELAY = 500L

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ACTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    val relocationRequester = remember {
        RelocationRequester()
    }
    val coroutineScope = rememberCoroutineScope()

    Column {
        OutlinedTextField(
            value = text, onValueChange = onTextChanged,
            label = {
                Text(text = labelText)
            },
            modifier = modifier
                .heightIn(dimensionResource(id = R.dimen.text_field_height))
                .fillMaxWidth()
                .relocationRequester(relocationRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        coroutineScope.launch {
                            delay(timeMillis = MILLI_SECONDS_DELAY)
                            relocationRequester.bringIntoView()
                        }
                    }
                },
            isError = (errorMessage != null),
            visualTransformation = visualTransformation,
            enabled = enabled,
            keyboardOptions = keyboardOptions,
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .padding(
                        top = 4.dp,
                        start = 16.dp
                    )
            )
        }
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
fun ACTextFieldPreview() {
    ACTasksTheme {
        Surface {
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                ACTextField(text = "Text Field Filled", onTextChanged = {}, labelText = "Label")
                ACTextField(text = "", onTextChanged = {}, labelText = "Enter a text...")
                ACTextField(
                    text = "",
                    onTextChanged = {},
                    labelText = "Enter a text...",
                    errorMessage = "This field is required"
                )
            }
        }
    }
}
