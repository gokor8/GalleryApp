package com.example.galleryapp.di

import com.example.data.api.UserService
import com.example.data.datasource.auth.ApiSignInDataSource
import com.example.data.datasource.auth.ApiSignUpDataSource
import com.example.data.managers.ApiTokenAccessManager
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.LoginServerErrorParserImpl
import com.example.data.parsers.RegistrationServerErrorParserImpl
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
    fun provideApiSignInDataSource(
        userService: UserService,
        apiTokenRegistrationManagerRead: ApiTokenRegistrationManager.Read,
        apiTokenAccessManagerSave: ApiTokenAccessManager.Save,
        loginServerErrorParser: LoginServerErrorParserImpl,
        gson: Gson
    ) = ApiSignInDataSource(
        userService,
        apiTokenRegistrationManagerRead,
        apiTokenAccessManagerSave,
        loginServerErrorParser,
        gson
    )

    @Provides
    fun provideApiSignUpDataSource(
        userService: UserService,
        apiTokenRegistrationManagerSave: ApiTokenRegistrationManager.Save,
        apiSignInDataSource: ApiSignInDataSource,
        registrationServerErrorParser: RegistrationServerErrorParserImpl,
        gson: Gson
    ) = ApiSignUpDataSource(
        userService,
        apiTokenRegistrationManagerSave,
        apiSignInDataSource,
        registrationServerErrorParser,
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