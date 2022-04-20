package com.example.galleryapp.di

import com.example.data.parsers.LoginServerErrorParserImpl
import com.example.data.parsers.RegistrationServerErrorParserImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ParsersModule {

    @Provides
    fun provideRegistrationServerErrorParserImpl() = RegistrationServerErrorParserImpl()

    @Provides
    fun provideLoginServerErrorParserImpl() = LoginServerErrorParserImpl()
}