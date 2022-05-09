package com.example.galleryapp.di

import androidx.fragment.app.Fragment
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.ui.adapters.models.BaseFragmentFactoryModelsImpl
import com.example.galleryapp.ui.adapters.models.TabFragmentFactoryModelsImpl
import com.example.galleryapp.ui.fragments.bnv.BaseListener
import com.example.galleryapp.ui.models.RecyclerViewImageHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    fun provideRecyclerViewImageHandler() = RecyclerViewImageHandler()

    @Provides
    fun provideBaseFragmentFactoryModelsImpl() = BaseFragmentFactoryModelsImpl()

    @Provides
    fun provideTabFragmentFactoryModelsImpl() = TabFragmentFactoryModelsImpl()

    @Provides
    fun provideBaseLazyFactory(
        tabFactoryModels: BaseFragmentFactoryModelsImpl
    ): LazyFactory<LazyFactory.Item<Int, Fragment>, Int, Fragment> =
        LazyFactory(tabFactoryModels)

    @Provides
    fun provideTabLazyFactory(
        factoryModelsTab: TabFragmentFactoryModelsImpl
    ): LazyFactory<TabFragmentFactoryModelsImpl.TabLazyFactoryItem, Int, Fragment> =
        LazyFactory(factoryModelsTab)

    @Provides
    @Singleton
    @Named("BaseStringListener")
    fun provideBaseStringListener() = BaseListener<String>()
}