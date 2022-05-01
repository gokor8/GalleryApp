package com.example.galleryapp.di

import android.app.Application
import com.example.galleryapp.ui.models.exceptions.auth.AuthExceptionMapUiFactory
import com.example.galleryapp.ui.models.exceptions.photos.PhotosFailMapUiFactory
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
        PhotosFailMapUiFactory(application.resources)
}