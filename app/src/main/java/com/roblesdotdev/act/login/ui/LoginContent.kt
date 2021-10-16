package com.roblesdotdev.act.login.ui

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.roblesdotdev.act.R
import com.roblesdotdev.act.core.ui.UIText
import com.roblesdotdev.act.core.ui.components.ACTextField
import com.roblesdotdev.act.core.ui.components.PrimaryButton
import com.roblesdotdev.act.core.ui.components.SecondaryButton
import com.roblesdotdev.act.core.ui.components.VerticalSpacer
import com.roblesdotdev.act.core.ui.getString
import com.roblesdotdev.act.core.ui.theme.ACTasksTheme
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.Email
import com.roblesdotdev.act.login.domain.model.Password

private const val APP_LOGO_WIDTH_PERCENTAGE = 0.5F

/**
 * This composable maintains the entire screen for handling user login.
 *
 * @param[viewState] The current state of the screen to render.
 * @param[onEmailChanged] A callback invoked when the user enters their email.
 * @param[onPasswordChanged] A callback invoked when the user enters their password.
 * @param[onLoginClicked] A callback invoked when the user clicks the login button.
 * @param[onSignupClicked] A callback invoked when the user clicks the sign up button.
 */
@Composable
fun LoginContent(
    viewState: LoginViewState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignupClicked: () -> Unit,
) {
    Surface(
        color = MaterialTheme.colors.background
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LogoInputsColumn(
                viewState,
                onEmailChanged,
                onPasswordChanged,
                onLoginClicked,
                onSignupClicked
            )
            CircularProgressIndicator(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.Center)
            )
        }
    }
}

@Composable
private fun LogoInputsColumn(
    viewState: LoginViewState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClicked: () -> Unit,
    onSignupClicked: () -> Unit
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

        EmailInput(
            text = viewState.credentials.email.value,
            onTextChanged = onEmailChanged,
            errorMessage = (viewState as? LoginViewState.Active)?.emailInputErrorMessage?.getString(),
        )

        VerticalSpacer(height = 12.dp)

        PasswordInput(
            text = viewState.credentials.password.value,
            onTextChanged = onPasswordChanged,
            errorMessage = (viewState as? LoginViewState.Active)?.passwordInputErrorMessage?.getString(),
        )

        if (viewState is LoginViewState.SubmissionError) {
            Text(
                text = viewState.errorMessage.getString(),
                color = MaterialTheme.colors.error,
                modifier = Modifier
                    .padding(top = 12.dp)
            )
        }

        VerticalSpacer(height = 48.dp)

        LoginButton(onClick = onLoginClicked, enabled = viewState.buttonsEnabled)

        VerticalSpacer(height = 12.dp)

        SignupButton(onClick = onSignupClicked, enabled = viewState.buttonsEnabled)
    }
}

@Composable
private fun SignupButton(onClick: () -> Unit, enabled: Boolean) {
    SecondaryButton(
        text = stringResource(R.string.sign_up),
        onClick = onClick,
        enabled = enabled,
    )
}

@Composable
private fun LoginButton(onClick: () -> Unit, enabled: Boolean) {
    PrimaryButton(
        text = stringResource(R.string.login),
        onClick = onClick,
        enabled = enabled,
    )
}

@Composable
private fun PasswordInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?
) {
    ACTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.password),
        errorMessage = errorMessage,
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Composable
private fun EmailInput(
    text: String,
    onTextChanged: (String) -> Unit,
    errorMessage: String?
) {
    ACTextField(
        text = text,
        onTextChanged = onTextChanged,
        labelText = stringResource(R.string.email),
        errorMessage = errorMessage,
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
fun LoginContentPreview(
    @PreviewParameter(LoginViewStateProvider::class)
    loginViewState: LoginViewState,
) {
    ACTasksTheme {
        LoginContent(
            viewState = loginViewState,
            onEmailChanged = {},
            onPasswordChanged = {},
            onLoginClicked = {},
            onSignupClicked = {}
        )
    }
}

class LoginViewStateProvider : PreviewParameterProvider<LoginViewState> {

    override val values: Sequence<LoginViewState>
        get() {
            val activeCredentials = Credentials(
                Email("email@test.com"),
                Password("password"),
            )

            return sequenceOf(
                LoginViewState.Initial,
                LoginViewState.Active(activeCredentials),
                LoginViewState.Submitting(activeCredentials),
                LoginViewState.SubmissionError(
                    credentials = activeCredentials,
                    errorMessage = UIText.StringText("Something went wrong."),
                ),
                LoginViewState.Active(
                    credentials = activeCredentials,
                    emailInputErrorMessage = UIText.ResourceText(R.string.err_empty_email),
                    passwordInputErrorMessage = UIText.ResourceText(R.string.err_empty_password),
                ),
            )
        }
}
