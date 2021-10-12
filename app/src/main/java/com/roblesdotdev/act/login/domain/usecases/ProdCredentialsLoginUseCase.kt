package com.roblesdotdev.act.login.domain.usecases

import com.roblesdotdev.act.core.data.Result
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.InvalidCredentialsException
import com.roblesdotdev.act.login.domain.model.LoginResult
import com.roblesdotdev.act.login.domain.repository.LoginRepository
import com.roblesdotdev.act.login.domain.repository.TokenRepository

/**
 * A concrete implementation of a [CredentialsLoginUseCase]
 */
class ProdCredentialsLoginUseCase(
    private val loginRepository: LoginRepository,
    private val tokenRepository: TokenRepository,
) : CredentialsLoginUseCase {

    override suspend fun invoke(credentials: Credentials): LoginResult {

        return when (val repoResult = loginRepository.login(credentials)) {
            is Result.Success -> {
                tokenRepository.storeToken(repoResult.data.token)
                LoginResult.Success
            }
            is Result.Error -> loginResultForError(repoResult)
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
