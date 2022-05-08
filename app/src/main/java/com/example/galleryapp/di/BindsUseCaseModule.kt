package com.example.galleryapp.di

import com.example.domain.usecases.LoadNewPhotosUseCase
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.domain.usecases.LoadPopularPhotosUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsUseCaseModule {

    @Binds
    @Named("LoadNewPhotosUseCase")
    abstract fun bindNewToLoadPhotosUseCase(loadNewPhotosUseCase: LoadNewPhotosUseCase): LoadPhotosUseCase

    @Binds
    @Named("LoadPopularPhotosUseCase")
    abstract fun bindPopularToLoadPhotosUseCase(loadPopularPhotosUseCase: LoadPopularPhotosUseCase): LoadPhotosUseCase
}