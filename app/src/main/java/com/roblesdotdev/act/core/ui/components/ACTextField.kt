package com.roblesdotdev.act.core.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme

@Composable
fun ACTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: String,
    modifier: Modifier = Modifier,
    errorMessage: String? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
) {
    Column {
        OutlinedTextField(
            value = text, onValueChange = onTextChanged,
            label = {
                Text(text = labelText)
            },
            modifier = modifier
                .heightIn(dimensionResource(id = R.dimen.text_field_height))
                .fillMaxWidth(),
            isError = (errorMessage != null),
            visualTransformation = visualTransformation,
            enabled = enabled,
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
