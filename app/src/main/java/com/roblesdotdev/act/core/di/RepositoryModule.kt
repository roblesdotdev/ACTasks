package com.roblesdotdev.act.core.di

import com.roblesdotdev.act.login.domain.repository.DemoLoginService
import com.roblesdotdev.act.login.domain.repository.DemoTokenService
import com.roblesdotdev.act.login.domain.repository.LoginRepository
import com.roblesdotdev.act.login.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindTokenRepository(
        tokenRepository: DemoTokenService
    ): TokenRepository

    @Binds
    abstract fun bindLoginRepository(
        loginRepository: DemoLoginService
    ): LoginRepository
}
