package com.example.galleryapp.di

import androidx.fragment.app.Fragment
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.ui.adapters.models.FactoryModelsImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsUiModule {

    @Binds
    abstract fun bindsFactoryModelsImpl(factoryModels: FactoryModelsImpl):
            FactoryModels<Int, Fragment>
}