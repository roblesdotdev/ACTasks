package com.roblesdotdev.act.login.ui

import com.roblesdotdev.act.CoroutinesTestRule
import com.roblesdotdev.act.R
import com.roblesdotdev.act.ThreadExceptionHandlerTestRule
import com.roblesdotdev.act.core.ui.UIText
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.Email
import com.roblesdotdev.act.login.domain.model.LoginResult
import com.roblesdotdev.act.login.domain.model.Password
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class LoginViewModelTest {
    private lateinit var testRobot: LoginViewModelRobot

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @get:Rule
    val threadExceptionHandlerTestRule = ThreadExceptionHandlerTestRule()

    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    @Test
    fun testInitialState() = runBlockingTest {
        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {},
                viewStates = listOf(LoginViewState.Initial)
            )
    }

    @Test
    fun testUpdateCredentials() = runBlockingTest {
        val testEmail = "test@email.com"
        val testPassword = "password"

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(value = testEmail)
            )
        )
        val emailPasswordEnteredState = LoginViewState.Active(
            credentials = Credentials(
                email = Email(value = testEmail),
                password = Password(value = testPassword)
            )
        )
        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            emailPasswordEnteredState,
        )

        testRobot
            .buildViewModel()
            .expectViewStates(
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                },
                viewStates = expectedViewStates
            )
    }

    @Test
    fun testSubmittingInvalidCredentials() = runBlockingTest {
        val testEmail = "test@email.com"
        val testPassword = "password"

        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword)
        )

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail))
        )
        val emailPasswordEnteredState = LoginViewState.Active(
            credentials = completedCredentials
        )
        val submittingState = LoginViewState.Submitting(
            credentials = completedCredentials
        )
        val submissionErrorState = LoginViewState.SubmissionError(
            credentials = completedCredentials,
            errorMessage = UIText.ResourceText(R.string.err_invalid_credentials)
        )

        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            emailPasswordEnteredState,
            submittingState,
            submissionErrorState,
        )

        testRobot
            .buildViewModel()
            .mockLoginResultForCredentials(
                credentials = completedCredentials,
                result = LoginResult.Failure.InvalidCredentials,
            )
            .expectViewStates(
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLogInButton()
                },
                viewStates = expectedViewStates
            )
    }

    @Test
    fun testUnknownLoginFailure() = runBlockingTest {
        val testEmail = "test@email.com"
        val testPassword = "password"

        val completedCredentials = Credentials(
            email = Email(testEmail),
            password = Password(testPassword)
        )

        val initialState = LoginViewState.Initial
        val emailEnteredState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail))
        )
        val emailPasswordEnteredState = LoginViewState.Active(
            credentials = completedCredentials
        )
        val submittingState = LoginViewState.Submitting(
            credentials = completedCredentials
        )
        val submissionErrorState = LoginViewState.SubmissionError(
            credentials = completedCredentials,
            errorMessage = UIText.ResourceText(R.string.err_login_failure)
        )

        val expectedViewStates = listOf(
            initialState,
            emailEnteredState,
            emailPasswordEnteredState,
            submittingState,
            submissionErrorState,
        )

        testRobot
            .buildViewModel()
            .mockLoginResultForCredentials(
                credentials = completedCredentials,
                result = LoginResult.Failure.Unknown,
            )
            .expectViewStates(
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLogInButton()
                },
                viewStates = expectedViewStates
            )
    }

    @Test
    fun testSubmitWithoutCredentials() = runBlockingTest {
        val credentials = Credentials()

        val initialState = LoginViewState.Initial
        val submittingState = LoginViewState.Submitting(
            credentials = credentials
        )
        val invalidInputState = LoginViewState.Active(
            credentials = Credentials(),
            emailInputErrorMessage = UIText.ResourceText(R.string.err_empty_email),
            passwordInputErrorMessage = UIText.ResourceText(R.string.err_empty_password),
        )

        testRobot
            .buildViewModel()
            .mockLoginResultForCredentials(
                credentials = credentials,
                result = LoginResult.Failure.EmptyCredentials(
                    emptyEmail = true,
                    emptyPassword = true,
                )
            )
            .expectViewStates(
                action = {
                    clickLogInButton()
                },
                viewStates = listOf(initialState, submittingState, invalidInputState)
            )
    }

    @Test
    fun testClearErrorsAfterInput() = runBlockingTest {
        val credentials = Credentials()
        val testEmail = "testy@mctestface.com"
        val testPassword = "Hunter2"

        val initialState = LoginViewState.Initial
        val submittingState = LoginViewState.Submitting(
            credentials = credentials,
        )
        val invalidInputsState = LoginViewState.Active(
            credentials = credentials,
            emailInputErrorMessage = UIText.ResourceText(R.string.err_empty_email),
            passwordInputErrorMessage = UIText.ResourceText(R.string.err_empty_password),
        )
        val emailInputState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail)),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = UIText.ResourceText(R.string.err_empty_password),
        )
        val passwordInputState = LoginViewState.Active(
            credentials = Credentials(email = Email(testEmail), password = Password(testPassword)),
            emailInputErrorMessage = null,
            passwordInputErrorMessage = null,
        )

        testRobot
            .buildViewModel()
            .mockLoginResultForCredentials(
                credentials = credentials,
                result = LoginResult.Failure.EmptyCredentials(
                    emptyEmail = true,
                    emptyPassword = true,
                )
            )
            .expectViewStates(
                action = {
                    clickLogInButton()
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                },
                viewStates = listOf(
                    initialState,
                    submittingState,
                    invalidInputsState,
                    emailInputState,
                    passwordInputState,
                ),
            )
    }
}
