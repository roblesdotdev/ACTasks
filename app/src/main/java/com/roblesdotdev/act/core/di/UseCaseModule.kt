package com.roblesdotdev.act.core.di

import com.roblesdotdev.act.login.domain.usecases.CredentialsLoginUseCase
import com.roblesdotdev.act.login.domain.usecases.ProdCredentialsLoginUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun bindCredentialsLoginUseCase(
        credentialsLoginUseCase: ProdCredentialsLoginUseCase
    ): CredentialsLoginUseCase
}
