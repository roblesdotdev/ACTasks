package com.roblesdotdev.act.login.domain.model

@JvmInline
value class AuthToken(val value: String)

@JvmInline
value class RefreshToken(val value: String)

/**
 * Contains the information necessary for authenticate network requests.
 *
 * @property[authToken] The current token used to validate user's request.
 * @property[refreshToken] A token used to generate a new [authToken] if the current is expired.
 */
data class Token(
    val authToken: AuthToken,
    val refreshToken: RefreshToken
)
