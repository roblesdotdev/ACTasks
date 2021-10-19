package com.roblesdotdev.act.login.ui

import com.roblesdotdev.act.core.ui.UIText
import com.roblesdotdev.act.login.domain.model.Credentials
import com.roblesdotdev.act.login.domain.model.Email
import com.roblesdotdev.act.login.domain.model.Password

sealed class LoginViewState(
    open val credentials: Credentials,
    open val buttonsEnabled: Boolean = true,
) {
    /**
     * The initial state of the screen without data.
     */
    object Initial : LoginViewState(
        credentials = Credentials(
            Email(""),
            Password("")
        )
    )

    /**
     * The state of the screen as the user is entering information.
     */
    data class Active(
        override val credentials: Credentials,
        val emailInputErrorMessage: UIText? = null,
        val passwordInputErrorMessage: UIText? = null,
    ) : LoginViewState(
        credentials = credentials,
    )

    /**
     * The state of the screen as the user is attempting to login.
     */
    data class Submitting(
        override val credentials: Credentials,
    ) : LoginViewState(
        credentials = credentials,
        buttonsEnabled = false,
    )

    /**
     * The state of the screen when there is an error logged in.
     */
    data class SubmissionError(
        override val credentials: Credentials,
        val errorMessage: UIText
    ) : LoginViewState(
        credentials = credentials,
    )

    /**
     * The state of the screen when logged in success.
     */
    object Completed : LoginViewState(
        credentials = Credentials(),
        buttonsEnabled = false,
    )
}
