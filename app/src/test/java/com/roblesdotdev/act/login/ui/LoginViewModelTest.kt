package com.roblesdotdev.act.login.ui

import com.roblesdotdev.act.CoroutinesTestRule
import com.roblesdotdev.act.R
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

    @Before
    fun setUp() {
        testRobot = LoginViewModelRobot()
    }

    @Test
    fun testInitialState() {
        testRobot
            .buildViewModel()
            .assertViewState(LoginViewState.Initial)
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
            .assertViewStatesAfterAction(
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
            .assertViewStatesAfterAction(
                action = {
                    enterEmail(testEmail)
                    enterPassword(testPassword)
                    clickLogInButton()
                },
                viewStates = expectedViewStates
            )
    }
}
