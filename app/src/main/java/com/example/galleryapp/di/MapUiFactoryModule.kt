package com.example.galleryapp.di

import android.app.Application
import com.example.galleryapp.ui.models.exceptions.auth.AuthMapUiFactory
import com.example.galleryapp.ui.models.exceptions.photos.PhotosMapUiFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapUiFactoryModule {
    @Provides
    fun provideAuthToMapUiFactory(application: Application) =
        AuthMapUiFactory(application.resources)

    @Provides
    fun providePhotosToMapUiFactory(application: Application) =
        PhotosMapUiFactory(application.resources)
}