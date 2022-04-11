package com.example.galleryapp.di

import android.content.Context
import com.example.data.storages.CacheService
import com.example.data.datasource.SharedPreferencesDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesService(@ApplicationContext context: Context): CacheService =
        SharedPreferencesDataSource(context)
}