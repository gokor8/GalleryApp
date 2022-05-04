package com.example.galleryapp.di

import androidx.fragment.app.Fragment
import com.example.domain.core.HandleFactory
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.domain.usecases.LoadPopularPhotosUseCase
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.ui.adapters.models.FragmentFactoryModelsImpl
import com.example.galleryapp.ui.models.RecyclerViewImageHandler
import com.example.galleryapp.ui.models.states.PaginationState
import com.example.galleryapp.ui.models.states.PhotosUiState
import com.example.galleryapp.ui.pagination.PhotosPagination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class UiModule {

    @Provides
    fun provideRecyclerViewImageHandler() = RecyclerViewImageHandler()

    @Provides
    fun provideFactoryModelsImpl() = FragmentFactoryModelsImpl()

    @Provides
    fun provideBaseLazyFactory(factoryModels: FactoryModels<Int, Fragment>) =
        LazyFactory(factoryModels)

    @Provides
    fun provideXuinya(): HandleFactory<PhotosState,PaginationState> = PhotosUiState.PhotosStateToPhotosUiStateMapper()

    @Provides
    fun providePhotosPagination(
        photosLoadPhotosUseCase: LoadPopularPhotosUseCase,
        handleFactory: HandleFactory<PhotosState, PaginationState>
    ) = PhotosPagination(photosLoadPhotosUseCase, handleFactory)
}