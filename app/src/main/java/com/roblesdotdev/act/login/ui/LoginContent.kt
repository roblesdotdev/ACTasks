package com.roblesdotdev.act.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.components.ACTextField
import com.roblesdotdev.act.core.ui.components.PrimaryButton
import com.roblesdotdev.act.core.ui.components.SecondaryButton
import com.roblesdotdev.act.core.ui.components.VerticalSpacer
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme

private const val APP_LOGO_WIDTH_PERCENTAGE = 0.5F

@Composable
fun LoginContent(
    viewState: LoginViewState,
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.screen_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.weight(1F))
            Image(
                painterResource(id = R.drawable.ic_actasks_logo),
                contentDescription = "App Logo",
                modifier = Modifier
                    .fillMaxWidth(APP_LOGO_WIDTH_PERCENTAGE)
            )
            Spacer(modifier = Modifier.weight(1F))

            ACTextField(text = viewState.username, onTextChanged = {}, labelText = "Username")
            VerticalSpacer(height = 12.dp)
            ACTextField(text = viewState.password, onTextChanged = {}, labelText = "Password")

            VerticalSpacer(height = 48.dp)

            PrimaryButton(text = "Login", onClick = { /*TODO*/ })

            VerticalSpacer(height = 12.dp)

            SecondaryButton(text = "Sign up", onClick = { /*TODO*/ })
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
fun LoginContentPreview() {
    val viewState = LoginViewState(username = "", password = "")
    ACTasksTheme {
        LoginContent(viewState = viewState)
    }
}
