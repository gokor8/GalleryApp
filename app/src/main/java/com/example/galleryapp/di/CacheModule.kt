package com.example.galleryapp.di

import android.content.Context
import com.example.data.storages.CacheService
import com.example.data.datasource.SharedPreferencesDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    fun provideSharedPreferencesService(@ApplicationContext context: Context): CacheService =
        SharedPreferencesDataSource(context)
}