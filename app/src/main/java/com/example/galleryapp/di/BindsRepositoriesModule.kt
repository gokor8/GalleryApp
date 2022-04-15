package com.example.galleryapp.di

import com.example.data.parsers.BaseServerErrorParserImpl
import com.example.data.parsers.ServerErrorParser
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface BindsRepositoriesModule {

    @Binds
    fun bindServerErrorParser(baseServerErrorParserImpl: BaseServerErrorParserImpl): ServerErrorParser
}