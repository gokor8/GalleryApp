package com.example.galleryapp.di

import com.example.data.parsers.RegistrationServerErrorParserImpl
import com.example.data.parsers.ServerErrorParser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsParsersModule {
    @Binds
    abstract fun bindServerErrorParser(registrationServerErrorParserImpl: RegistrationServerErrorParserImpl): ServerErrorParser
}