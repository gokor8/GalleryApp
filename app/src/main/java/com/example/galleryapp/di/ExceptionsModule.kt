package com.example.galleryapp.di

import android.app.Application
import com.example.galleryapp.ui.models.exceptions.DataToUiExceptionFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ExceptionsModule {
    @Provides
    fun provideDataToUiExceptionFactory(application: Application) = DataToUiExceptionFactory(application.resources)
}