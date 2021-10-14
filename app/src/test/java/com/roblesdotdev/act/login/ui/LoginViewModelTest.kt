package com.roblesdotdev.act.login.ui

import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.Email
import com.roblesdotdev.act.login.domain.model.Password
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class LoginViewModelTest {
    private lateinit var testRobot: LoginViewModelRobot

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
}
