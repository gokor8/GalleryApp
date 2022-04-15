package com.example.galleryapp.di

import com.example.domain.repository.AuthorizationRepository
import com.example.domain.usecases.RegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    // TODO @Binds
    @Provides
    fun provideRegistrationUseCase(authorizationRepository: AuthorizationRepository) =
        RegistrationUseCase(authorizationRepository)
}