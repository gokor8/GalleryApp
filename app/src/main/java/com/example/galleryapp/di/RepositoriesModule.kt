package com.example.galleryapp.di

import com.example.data.api.services.ImageService
import com.example.data.api.services.UserService
import com.example.data.datasource.auth.ApiSignInDataSource
import com.example.data.datasource.auth.ApiSignUpDataSource
import com.example.data.datasource.pictures.PicturesDataSource
import com.example.data.managers.ApiTokenAccessManager
import com.example.data.managers.ApiTokenRegistrationManager
import com.example.data.parsers.LoginServerErrorParserImpl
import com.example.data.parsers.RegistrationServerErrorParserImpl
import com.example.data.repository.MediaRepositoryImpl
import com.example.data.repository.UserAuthorizationRepositoryImpl
import com.example.domain.core.HandleFactory
import com.example.data.core.photos.PhotosServerState
import com.example.domain.core.UiFailModel
import com.example.domain.repository.AuthorizationRepository
import com.example.domain.repository.MediaRepository
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
    fun provideUserAuthRepository(
        authApiDataSource: ApiSignUpDataSource,
        apiSignInDataSource: ApiSignInDataSource,
    ) =
        UserAuthorizationRepositoryImpl(
            authApiDataSource,
            apiSignInDataSource,
        )


    //Media repository
    @Provides
    fun provideMediaRepository(picturesDataSource: PicturesDataSource): MediaRepository =
        MediaRepositoryImpl(picturesDataSource)
}