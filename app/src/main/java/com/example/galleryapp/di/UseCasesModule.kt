package com.example.galleryapp.di

import com.example.domain.core.ExceptionFactory
import com.example.domain.repository.AuthorizationRepository
import com.example.domain.usecases.AuthorizationUseCase
import com.example.domain.usecases.RegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun provideRegistrationUseCase(authorizationRepository: AuthorizationRepository, exceptionFactory: ExceptionFactory) =
        RegistrationUseCase(authorizationRepository, exceptionFactory)

    @Provides
    fun provideAuthorizationUseCase(authorizationRepository: AuthorizationRepository, exceptionFactory: ExceptionFactory) =
        AuthorizationUseCase(authorizationRepository, exceptionFactory)
}