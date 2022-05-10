package com.example.galleryapp.di

import androidx.fragment.app.Fragment
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.ui.adapters.models.TabFragmentFactoryItemsContainerImpl
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
    fun provideTabFragmentFactoryModelsImpl() = TabFragmentFactoryItemsContainerImpl()

    @Provides
    fun provideTabLazyFactory(
        factoryModelsTab: TabFragmentFactoryItemsContainerImpl
    ): LazyFactory<TabFragmentFactoryItemsContainerImpl.TabLazyFactoryItem, Int, Fragment> =
        LazyFactory(factoryModelsTab)

    @Provides
    @Singleton
    @Named("BaseStringListener")
    fun provideBaseStringListener() = BaseListener<String>()
}