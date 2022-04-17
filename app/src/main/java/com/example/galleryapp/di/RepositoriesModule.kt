package com.example.galleryapp.di

import com.example.data.api.TokenService
import com.example.data.api.UserService
import com.example.data.datasource.ApiAuthDataSource
import com.example.data.datasource.ApiTokenDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.managers.ApiTokenManager
import com.example.data.parsers.BaseServerErrorParserImpl
import com.example.data.parsers.ServerErrorParser
import com.example.data.repository.UserAuthorizationRepositoryImpl
import com.example.domain.repository.AuthorizationRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    fun provideBaseServerErrorParserImpl() = BaseServerErrorParserImpl()

    @Provides
    fun provideApiAuth(userService: UserService, serverErrorParser: ServerErrorParser, gson: Gson) =
        ApiAuthDataSource(userService, serverErrorParser, gson)

    @Provides
    fun provideCloudTokenDataSource(tokenService: TokenService) = ApiTokenDataSource(tokenService)

    @Provides
    fun provideApiTokenManager(
        apiTokenDataSource: ApiTokenDataSource,
        sharedPreferencesDataSource: SharedPreferencesDataSource
    ) = ApiTokenManager(apiTokenDataSource, sharedPreferencesDataSource)

    @Provides
    fun provideUserAuthRepository(
        authApiDataSource: ApiAuthDataSource,
        apiTokenManager: ApiTokenManager
    ): AuthorizationRepository =
        UserAuthorizationRepositoryImpl(authApiDataSource, apiTokenManager)
}