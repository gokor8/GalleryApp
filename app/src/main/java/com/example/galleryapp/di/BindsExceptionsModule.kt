package com.example.galleryapp.di

import com.example.data.parsers.RegistrationServerErrorParserImpl
import com.example.data.parsers.ServerErrorParser
import com.example.domain.core.ExceptionFactory
import com.example.galleryapp.ui.models.exceptions.DataToUiExceptionFactory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsExceptionsModule {

    @Binds
    abstract fun bindServerErrorParser(dataToUiExceptionFactory: DataToUiExceptionFactory): ExceptionFactory
}