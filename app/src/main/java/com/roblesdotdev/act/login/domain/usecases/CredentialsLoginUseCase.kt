package com.roblesdotdev.act.login.domain.usecases

import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.LoginResult

/**
 * This use case consumes any information required to log in the user, and attempts to do so.
 */
interface CredentialsLoginUseCase {
    suspend operator fun invoke(
        credentials: Credentials
    ): LoginResult
}
