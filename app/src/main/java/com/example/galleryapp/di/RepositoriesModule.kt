package com.example.galleryapp.di

import com.example.data.api.ApiSignUpDataSource
import com.example.data.repository.UserAuthorizationRepository
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
        apiSignUpDataSource: ApiSignUpDataSource,
        sharedPreferencesService: CacheService
    ): AuthorizationRepository =
        UserAuthorizationRepository(apiSignUpDataSource, sharedPreferencesService)
}