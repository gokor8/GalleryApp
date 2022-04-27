package com.example.galleryapp.di

import androidx.fragment.app.Fragment
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.ui.adapters.models.FactoryModelsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    fun provideFactoryModelsImpl() = FactoryModelsImpl()

    @Provides
    fun provideBaseLazyFactory(factoryModels: FactoryModels<Int, Fragment>) = LazyFactory(factoryModels)
}