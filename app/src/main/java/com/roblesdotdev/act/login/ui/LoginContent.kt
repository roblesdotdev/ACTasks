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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.components.ACTextField
import com.roblesdotdev.act.core.ui.components.PrimaryButton
import com.roblesdotdev.act.core.ui.components.SecondaryButton
import com.roblesdotdev.act.core.ui.components.VerticalSpacer
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme

private const val APP_LOGO_WIDTH_PERCENTAGE = 0.5F

/**
 * This composable maintains the entire screen for handling user login.
 *
 * @param[viewState] The current state of the screen to render.
 * @param[onUsernameChanged] A callback invoked when the user enters their username.
 * @param[onPasswordChanged] A callback invoked when the user enters their password.
 * @param[onLoginClicked] A callback invoked when the user clicks the login button.
 * @param[onSignupClicked] A callback invoked when the user clicks the sign up button.
 */
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onUsernameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignupClicked: () -> Unit,
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

            AppLogo()

            Spacer(modifier = Modifier.weight(1F))

            UsernameInput(text = viewState.username, onTextChanged = onUsernameChanged)

            VerticalSpacer(height = 12.dp)

            PasswordInput(text = viewState.password, onTextChanged = onPasswordChanged)

            VerticalSpacer(height = 48.dp)

            LoginButton(onClick = onLoginClicked)

            VerticalSpacer(height = 12.dp)

            SignupButton(onClick = onSignupClicked)
        }
    }
}

@Composable
private fun SignupButton(onClick: () -> Unit) {
    SecondaryButton(
        text = stringResource(R.string.sign_up),
        onClick = onClick
    )
}

@Composable
private fun LoginButton(onClick: () -> Unit) {
    PrimaryButton(
        text = stringResource(R.string.login),
        onClick = onClick
    )
}

@Composable
private fun PasswordInput(text: String, onTextChanged: (String) -> Unit) {
    ACTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.password)
    )
}

@Composable
private fun UsernameInput(text: String, onTextChanged: (String) -> Unit) {
    ACTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.username)
    )
}

@Composable
private fun AppLogo() {
    Image(
        painterResource(id = R.drawable.ic_actasks_logo),
        contentDescription = "App Logo",
        modifier = Modifier
            .fillMaxWidth(APP_LOGO_WIDTH_PERCENTAGE)
    )
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
        LoginContent(
            viewState = viewState,
            onUsernameChanged = {},
            onPasswordChanged = {},
            onLoginClicked = {},
            onSignupClicked = {}
        )
    }
}
