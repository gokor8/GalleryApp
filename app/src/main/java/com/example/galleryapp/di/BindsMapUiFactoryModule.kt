package com.example.galleryapp.di

import com.example.data.core.photos.PhotosServerState
import com.example.domain.core.HandleFactory
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.PhotosState
import com.example.galleryapp.ui.mappers.AuthExceptionMapUiFactory
import com.example.galleryapp.ui.mappers.PhotosFailMapUiHandler
import com.example.galleryapp.ui.mappers.PhotosStateToPhotosUiStateMapper
import com.example.galleryapp.ui.models.states.PhotosUiState
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsMapUiFactoryModule {

    @Binds
    abstract fun bindAuthToMapUiFactory(authExceptionToUiExceptionFactory: AuthExceptionMapUiFactory):
            HandleFactory<Exception, UiFailModel>

    @Binds
    abstract fun bindPhotosToMapUiFactory(failMapUiHandler: PhotosFailMapUiHandler):
            HandleFactory<PhotosServerState, UiFailModel>

    @Binds
    abstract fun bindPhotosStateToPhotosUiStateMapper(photosStateToPhotosUiStateMapper: PhotosStateToPhotosUiStateMapper):
            HandleFactory<PhotosState, PhotosUiState>
}