package com.example.galleryapp.di

import com.example.data.storages.Cache
import com.example.data.datasource.SharedPreferencesDataSource
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
    abstract fun bindsSharedPreferencesService(sharedPreferencesDataSource: SharedPreferencesDataSource): Cache
}