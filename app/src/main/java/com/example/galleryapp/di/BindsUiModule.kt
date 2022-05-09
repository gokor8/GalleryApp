package com.example.galleryapp.di

import androidx.fragment.app.Fragment
import androidx.navigation.Navigator
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.core.ui.Listener
import com.example.galleryapp.ui.adapters.models.BaseFragmentFactoryModelsImpl
import com.example.galleryapp.ui.adapters.models.TabFragmentFactoryModelsImpl
import com.example.galleryapp.ui.fragments.bnv.BaseListener
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class BindsUiModule {

    //@Binds
    //abstract fun bindsRecyclerViewImageHandler(recyclerViewImageHandler: RecyclerViewImageHandler): ImageHandler

    /*@Named("BindBaseFactoryModels")
    @Binds
    abstract fun bindsBaseFactoryModelsImpl(baseFragmentFactoryModels: BaseFragmentFactoryModelsImpl): FactoryModels<LazyFactory.Item<Int, Fragment>>*/

    @Binds
    abstract fun bindsReadBaseStringListener(@Named("BaseStringListener") baseListener: BaseListener<String>): Listener.Read<String>

    @Binds
    abstract fun bindsSaveBaseStringListener(@Named("BaseStringListener") baseListener: BaseListener<String>): Listener.Save<String>
}