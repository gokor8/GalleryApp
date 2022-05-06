package com.example.galleryapp.di

import com.example.data.datasource.SharedPreferencesDataSource
import com.example.data.storages.CacheSharedPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    @Singleton
    abstract fun bindsSharedPreferencesService(sharedPreferencesDataSource: SharedPreferencesDataSource): CacheSharedPreferences.Mutable
}