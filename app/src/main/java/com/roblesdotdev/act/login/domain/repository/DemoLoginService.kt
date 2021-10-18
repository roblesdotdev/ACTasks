package com.roblesdotdev.act.login.domain.repository

import com.roblesdotdev.act.core.data.Result
import com.roblesdotdev.act.login.domain.model.AuthToken
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.LoginResponse
import com.roblesdotdev.act.login.domain.model.RefreshToken
import com.roblesdotdev.act.login.domain.model.Token
import javax.inject.Inject

/**
 * This is a sample [LoginRepository] that does not interact with any real data source, but allows
 * us to quickly modify return values for manual testing sake.
 */
class DemoLoginService @Inject constructor() : LoginRepository {
    override suspend fun login(credentials: Credentials): Result<LoginResponse> {
        val defaultToken = Token(
            AuthToken(""),
            RefreshToken("")
        )
        val defaultResponse = LoginResponse(defaultToken)

        return Result.Success(defaultResponse)
    }
}
