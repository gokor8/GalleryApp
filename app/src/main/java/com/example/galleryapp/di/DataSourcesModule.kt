package com.example.galleryapp.di

import com.example.data.api.services.ImageService
import com.example.data.api.services.TokenService
import com.example.data.api.services.UserService
import com.example.data.core.photos.PhotosServerState
import com.example.data.datasource.auth.ApiSignInDataSource
import com.example.data.datasource.auth.ApiSignUpDataSource
import com.example.data.datasource.auth.ApiTokenRegistrationDataSource
import com.example.data.datasource.pictures.PicturesDataSource
import com.example.data.managers.ApiTokenAccessManager
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.LoginServerErrorParserImpl
import com.example.data.parsers.RegistrationServerErrorParserImpl
import com.example.domain.core.HandleFactory
import com.example.domain.core.UiFailModel
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataSourcesModule {

    //Authorization
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
    fun provideCloudTokenDataSource(
        tokenService: TokenService,
        @Named("default_token") defaultToken: String
    ) =
        ApiTokenRegistrationDataSource(tokenService, defaultToken)

    //Media
    @Provides
    fun providePicturesDataSource(uiHandleFactory: HandleFactory<PhotosServerState, UiFailModel>, imageService: ImageService) =
        PicturesDataSource(imageService, uiHandleFactory)
}