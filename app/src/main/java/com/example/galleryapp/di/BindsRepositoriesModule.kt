package com.example.galleryapp.di

import com.example.data.parsers.BaseServerErrorParserImpl
import com.example.data.parsers.ServerErrorParser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsRepositoriesModule {

    @Binds
    abstract fun bindServerErrorParser(baseServerErrorParserImpl: BaseServerErrorParserImpl): ServerErrorParser
}