package com.roblesdotdev.act.login.domain.repository

import com.roblesdotdev.act.core.data.Result
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.LoginResponse

interface LoginRepository {
    /**
     * Given some user [credentials], try login the user.
     *
     * @return [Result] that contains the [LoginResponse] if successful, or an error otherwise.
     */
    suspend fun login(
        credentials: Credentials
    ): Result<LoginResponse>
}
