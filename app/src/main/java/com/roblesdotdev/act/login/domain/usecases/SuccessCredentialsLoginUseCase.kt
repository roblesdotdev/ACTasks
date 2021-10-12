package com.roblesdotdev.act.login.domain.usecases

import com.roblesdotdev.act.login.domain.model.LoginResult

/**
 * This is a temporary class to create a mock implementation of [CredentialsLoginUseCase]
 * that is always successful.
 */
class SuccessCredentialsLoginUseCase : CredentialsLoginUseCase {
    override suspend fun login(email: Email, password: Password): LoginResult {
        return LoginResult.Success
    }
}
