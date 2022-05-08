package com.example.galleryapp.di

import androidx.fragment.app.Fragment
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.ui.Listener
import com.example.galleryapp.ui.adapters.models.FragmentFactoryModelsImpl
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

    @Binds
    abstract fun bindsFactoryModelsImpl(fragmentFactoryModels: FragmentFactoryModelsImpl):
            FactoryModels<Int, Fragment>

    @Binds
    abstract fun bindsReadBaseStringListener(@Named("BaseStringListener") baseListener: BaseListener<String>): Listener.Read<String>

    @Binds
    abstract fun bindsSaveBaseStringListener(@Named("BaseStringListener") baseListener: BaseListener<String>): Listener.Save<String>
}