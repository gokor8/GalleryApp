package com.example.galleryapp.di

import com.example.data.datasource.CloudAuthDataSource
import com.example.data.repository.UserAuthorizationRepositoryImpl
import com.example.data.storages.CacheService
import com.example.domain.repository.AuthorizationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    fun provideUserAuthRepository(
        authCloudDataSource: CloudAuthDataSource,
        sharedPreferencesService: CacheService
    ): AuthorizationRepository =
        UserAuthorizationRepositoryImpl(authCloudDataSource, sharedPreferencesService)
}