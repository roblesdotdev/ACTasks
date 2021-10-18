package com.roblesdotdev.act.login.domain.usecases

import com.roblesdotdev.act.core.data.Result
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.InvalidCredentialsException
import com.roblesdotdev.act.login.domain.model.LoginResult
import com.roblesdotdev.act.login.domain.repository.LoginRepository
import com.roblesdotdev.act.login.domain.repository.TokenRepository
import javax.inject.Inject

/**
 * A concrete implementation of a [CredentialsLoginUseCase]
 */
class ProdCredentialsLoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
) : CredentialsLoginUseCase {

    override suspend fun invoke(credentials: Credentials): LoginResult {
        val validationResult = validateCredentials(credentials)

        if (validationResult != null) {
            return validationResult
        }

        return when (val repoResult = loginRepository.login(credentials)) {
            is Result.Success -> {
                tokenRepository.storeToken(repoResult.data.token)
                LoginResult.Success
            }
            is Result.Error -> loginResultForError(repoResult)
        }
    }

    private fun validateCredentials(credentials: Credentials): LoginResult.Failure.EmptyCredentials? {
        val emptyEmail = credentials.email.value.isEmpty()
        val emptyPassword = credentials.password.value.isEmpty()

        return if (emptyEmail || emptyPassword) {
            LoginResult.Failure.EmptyCredentials(
                emptyEmail = emptyEmail,
                emptyPassword = emptyPassword
            )
        } else {
            null
        }
    }

    /**
     * Checks the possible error scenarios for [repoResult] and maps to an
     * appropriate [LoginResult]
     */
    private fun loginResultForError(repoResult: Result.Error) =
        when (repoResult.error) {
            is InvalidCredentialsException -> LoginResult.Failure.InvalidCredentials
            else -> LoginResult.Failure.Unknown
        }
}
