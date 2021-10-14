package com.roblesdotdev.act.login.ui

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.roblesdotdev.act.fakes.FakeCredentialsLoginUseCase
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.LoginResult
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class LoginViewModelRobot {
    private val fakeCredentialsLoginUseCase = FakeCredentialsLoginUseCase()
    private lateinit var viewModel: LoginViewModel

    fun buildViewModel() = apply {
        viewModel = LoginViewModel(
            credentialsLoginUseCase = fakeCredentialsLoginUseCase.mock
        )
    }

    fun mockLoginResultForCredentials(
        credentials: Credentials,
        result: LoginResult
    ) = apply {
        fakeCredentialsLoginUseCase.mockLoginResultForCredentials(
            credentials = credentials,
            result = result
        )
    }

    fun enterEmail(email: String) = apply {
        viewModel.emailChanged(email)
    }

    fun enterPassword(password: String) = apply {
        viewModel.passwordChanged(password)
    }

    fun clickLogInButton() = apply {
        viewModel.loginButtonClicked()
    }

    fun clickedSignInButton() = apply {
        viewModel.signUpButtonClicked()
    }

    /**
     * Launch a coroutine that will observe our [viewModel]'s view state and ensure that we consume
     * all of the supplied [viewStates] in the same order that they are supplied.
     *
     * We should call this near the front of the test, to ensure that every view state we emit
     * can be collected by this.
     */
    suspend fun expectViewStates(viewStates: List<LoginViewState>) = coroutineScope {
        launch {
            viewModel.viewState.test {
                for (state in viewStates) {
                    assertThat(awaitItem()).isEqualTo(state)
                }
                this.cancel()
            }
        }
        return@coroutineScope this@LoginViewModelRobot
    }
}
