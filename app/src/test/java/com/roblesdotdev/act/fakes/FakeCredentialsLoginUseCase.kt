package com.roblesdotdev.act.fakes

import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.LoginResult
import com.roblesdotdev.act.login.domain.usecases.CredentialsLoginUseCase
import io.mockk.coEvery
import io.mockk.mockk

class FakeCredentialsLoginUseCase {
    val mock: CredentialsLoginUseCase = mockk()

    fun mockLoginResultForCredentials(
        credentials: Credentials,
        result: LoginResult
    ) {
        coEvery {
            mock(credentials = credentials)
        } returns result
    }
}
