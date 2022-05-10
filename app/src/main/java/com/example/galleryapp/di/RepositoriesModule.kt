package com.example.galleryapp.di

import com.example.data.datasource.auth.ApiSignInDataSource
import com.example.data.datasource.auth.ApiSignUpDataSource
import com.example.data.datasource.pictures.PicturesDataSource
import com.example.data.repository.MediaRepositoryImpl
import com.example.data.repository.UserAuthorizationRepositoryImpl
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