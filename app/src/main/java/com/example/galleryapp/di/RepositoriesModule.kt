package com.example.galleryapp.di

import com.example.data.api.TokenService
import com.example.data.api.UserService
import com.example.data.datasource.ApiSignInDataSource
import com.example.data.datasource.ApiSignUpDataSource
import com.example.data.datasource.ApiTokenRegistrationDataSource
import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.managers.ApiTokenManagerBabijon
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.LoginServerErrorParserImpl
import com.example.data.parsers.RegistrationServerErrorParserImpl
import com.example.data.parsers.ServerErrorParser
import com.example.data.repository.UserAuthorizationRepositoryImpl
import com.example.domain.repository.AuthorizationRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideGson() = Gson()

    @Provides
    fun provideApiSignUpDataSource(
        userService: UserService,
        apiTokenRegistrationManagerSave: ApiTokenRegistrationManager.Save,
        registrationServerErrorParser: RegistrationServerErrorParserImpl,
        gson: Gson
    ) = ApiSignUpDataSource(
        userService,
        apiTokenRegistrationManagerSave,
        registrationServerErrorParser,
        gson
    )

    @Provides
    fun provideApiSignInDataSource(
        userService: UserService,
        apiTokenRegistrationManagerRead: ApiTokenRegistrationManager.Read,
        sharedPreferencesDataSource: SharedPreferencesDataSource,
        loginServerErrorParser: LoginServerErrorParserImpl,
        gson: Gson
    ) = ApiSignInDataSource(
        userService,
        apiTokenRegistrationManagerRead,
        sharedPreferencesDataSource,
        loginServerErrorParser,
        gson
    )

    @Provides
    fun provideUserAuthRepository(
        authApiDataSource: ApiSignUpDataSource,
        apiSignInDataSource: ApiSignInDataSource,
    ): AuthorizationRepository =
        UserAuthorizationRepositoryImpl(
            authApiDataSource,
            apiSignInDataSource,
        )
}