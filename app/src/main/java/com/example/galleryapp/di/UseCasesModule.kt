package com.example.galleryapp.di

import com.example.domain.core.handle_factories.HandleFactory
import com.example.domain.core.UiFailModel
import com.example.domain.repository.AuthorizationRepository
import com.example.domain.repository.MediaRepository
import com.example.domain.usecases.AuthorizationUseCase
import com.example.domain.usecases.LoadNewPhotosUseCase
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.domain.usecases.RegistrationUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {

    @Provides
    fun provideRegistrationUseCase(
        authorizationRepository: AuthorizationRepository,
        mapUiFactory: HandleFactory<Exception, UiFailModel>
    ) =
        RegistrationUseCase(authorizationRepository, mapUiFactory)

    @Provides
    fun provideAuthorizationUseCase(
        authorizationRepository: AuthorizationRepository,
        mapUiFactory: HandleFactory<Exception, UiFailModel>
    ) = AuthorizationUseCase(authorizationRepository, mapUiFactory)

    @Provides
    fun provideLoadNewPhotosUseCase(mediaRepository: MediaRepository) =
        LoadNewPhotosUseCase(mediaRepository)

    @Provides
    fun provideLoadPopularPhotosUseCase(mediaRepository: MediaRepository) =
        LoadPopularPhotosUseCase(mediaRepository)
}