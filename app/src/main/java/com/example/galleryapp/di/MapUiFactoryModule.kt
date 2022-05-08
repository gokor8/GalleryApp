package com.example.galleryapp.di

import android.app.Application
import com.example.galleryapp.ui.mappers.AuthExceptionMapUiFactory
import com.example.galleryapp.ui.mappers.PhotosFailMapUiHandler
import com.example.galleryapp.ui.mappers.PhotosStateToPhotosUiStateMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapUiFactoryModule {
    @Provides
    fun provideAuthToMapUiFactory(application: Application) =
        AuthExceptionMapUiFactory(application.resources)

    @Provides
    fun providePhotosToMapUiFactory(application: Application) =
        PhotosFailMapUiHandler(application.resources)

    @Provides
    fun providePhotosStateToPhotosUiStateMapper() = PhotosStateToPhotosUiStateMapper()
}