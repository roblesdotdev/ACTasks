package com.roblesdotdev.act.login.domain.repository

import com.roblesdotdev.act.login.domain.model.Token

/**
 * This repository is responsible for fetching and storing a user's auth token.
 */
interface TokenRepository {
    /**
     * Given a [token], store this somewhere so it can be retrieved later.
     */
    suspend fun storeToken(
        token: Token
    )

    /**
     * Fetches the token of the signed is user, if we have one saved.
     *
     * @return The token or null if not found.
     */
    suspend fun fetchToken(): Token?
}
