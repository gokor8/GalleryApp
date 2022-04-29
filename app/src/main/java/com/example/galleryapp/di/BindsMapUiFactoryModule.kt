package com.example.galleryapp.di

import com.example.domain.core.HandleFactory
import com.example.domain.entities.photos.PhotosFailds
import com.example.galleryapp.ui.models.exceptions.auth.AuthMapUiFactory
import com.example.galleryapp.ui.models.exceptions.photos.PhotosMapUiFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsMapUiFactoryModule {

    @Binds
    abstract fun bindAuthToMapUiFactory(authToUiExceptionFactory: AuthMapUiFactory): HandleFactory<Exception>

    @Binds
    abstract fun bindPhotosToMapUiFactory(mapUiFactory: PhotosMapUiFactory): HandleFactory<PhotosFailds>
}