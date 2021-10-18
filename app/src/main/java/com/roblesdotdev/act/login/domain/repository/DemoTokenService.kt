package com.roblesdotdev.act.login.domain.repository

import com.roblesdotdev.act.login.domain.model.Token
import javax.inject.Inject

/**
 * This is a sample [TokenRepository] that does not interact with any real data source, but allows
 * us to quickly modify return values for manual testing sake.
 */
class DemoTokenService @Inject constructor() : TokenRepository {
    override suspend fun storeToken(token: Token) {
        // No-Op
    }

    override suspend fun fetchToken(): Token? {
        return null
    }
}
