package com.roblesdotdev.act.login.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.roblesdotdev.act.core.data.Result
import com.roblesdotdev.act.fakes.FakeLoginRepository
import com.roblesdotdev.act.fakes.FakeTokenRepository
import com.roblesdotdev.act.login.domain.model.AuthToken
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.Email
import com.roblesdotdev.act.login.domain.model.InvalidCredentialsException
import com.roblesdotdev.act.login.domain.model.LoginResponse
import com.roblesdotdev.act.login.domain.model.LoginResult
import com.roblesdotdev.act.login.domain.model.Password
import com.roblesdotdev.act.login.domain.model.RefreshToken
import com.roblesdotdev.act.login.domain.model.Token
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ProdCredentialsLoginUseCaseTest {
    private val defaultCredentials = Credentials(
        email = Email("fake@email.com"),
        password = Password("fake.password")
    )
    private val defaultToken = Token(
        authToken = AuthToken("Auth"),
        refreshToken = RefreshToken("Refresh")
    )
    private lateinit var loginRepository: FakeLoginRepository
    private lateinit var tokenRepository: FakeTokenRepository

    @Before
    fun setUp() {
        loginRepository = FakeLoginRepository()
        tokenRepository = FakeTokenRepository()
    }

    @Test
    fun testSuccessfulLogin() = runBlockingTest {
        val loginResponse = Result.Success(
            LoginResponse(token = defaultToken)
        )
        loginRepository.mockWithCredentials(defaultCredentials, loginResponse)
        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Success)
        tokenRepository.verifyTokenStored(defaultToken)
    }

    @Test
    fun testUnknownFailureLogin() = runBlockingTest {
        val loginResponse = Result.Error(
            Throwable("Fail...")
        )
        loginRepository.mockWithCredentials(defaultCredentials, loginResponse)
        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.Unknown)
        tokenRepository.verifyNoTokenStored()
    }

    @Test
    fun testInvalidCredentialsLogin() = runBlockingTest {
        val loginResponse = Result.Error(
            InvalidCredentialsException()
        )
        loginRepository.mockWithCredentials(defaultCredentials, loginResponse)
        val useCase = ProdCredentialsLoginUseCase(
            loginRepository = loginRepository.mock,
            tokenRepository = tokenRepository.mock,
        )

        val result = useCase(defaultCredentials)
        assertThat(result).isEqualTo(LoginResult.Failure.InvalidCredentials)
        tokenRepository.verifyNoTokenStored()
    }
}
