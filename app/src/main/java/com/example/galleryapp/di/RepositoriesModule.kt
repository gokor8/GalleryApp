package com.example.galleryapp.di

import android.content.Context
import com.example.data.api.TokenService
import com.example.data.api.UserService
import com.example.data.datasource.CloudAuthDataSource
import com.example.data.datasource.CloudTokenDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.managers.ApiTokenManager
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
    fun provideApiAuth(userService: UserService) = CloudAuthDataSource(userService)

    @Provides
    fun provideCloudTokenDataSource(tokenService: TokenService) = CloudTokenDataSource(tokenService)

    @Provides
    fun provideApiTokenManager(
        cloudTokenDataSource: CloudTokenDataSource,
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ) = ApiTokenManager(cloudTokenDataSource, sharedPreferencesDataSource)

    @Provides
    fun provideUserAuthRepository(
        authCloudDataSource: CloudAuthDataSource,
        apiTokenManager: ApiTokenManager
    ): AuthorizationRepository =
        UserAuthorizationRepositoryImpl(authCloudDataSource, apiTokenManager)
}