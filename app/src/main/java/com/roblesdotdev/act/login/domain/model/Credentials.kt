package com.roblesdotdev.act.login.domain.model

@Suppress("UnusedPrivateMember")
@JvmInline
value class Email(val value: String)

@Suppress("UnusedPrivateMember")
@JvmInline
value class Password(val value: String)

data class Credentials(
    val email: Email = Email(value = ""),
    val password: Password = Password(value = ""),
)
