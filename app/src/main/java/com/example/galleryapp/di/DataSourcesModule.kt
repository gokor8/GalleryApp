package com.example.galleryapp.di

import com.example.data.api.TokenService
import com.example.data.datasource.ApiTokenRegistrationDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class DataSourcesModule {

    @Provides
    fun provideCloudTokenDataSource(
        tokenService: TokenService,
        @Named("default_token") defaultToken: String
    ) =
        ApiTokenRegistrationDataSource(tokenService, defaultToken)
}