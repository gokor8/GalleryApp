package com.example.galleryapp.ui.models.states

import com.example.domain.core.HandleFactory
import com.example.domain.core.MapperFrom
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.PhotosState
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel

sealed class PhotosUiState : UiState, PaginationState {

    interface MapperToPhotosUiState<I> : MapperFrom<I, PhotosUiState>

    class PhotosStateToPhotosUiStateMapper : HandleFactory<PhotosState, PaginationState> {
        override fun handle(e: PhotosState): PhotosUiState =
            when (e) {
                is PhotosState.Success -> Success().mapTo(e)
                is PhotosState.Fail -> Error(e.errorContainer)
                is PhotosState.Exception -> Exception()
            }
    }

    class Success(override val paginationDataList: List<PictureInfoUiModel>) :
        PhotosUiState(), UiState.Success,
        PaginationState.Success<PictureInfoUiModel>,
        MapperToPhotosUiState<PhotosState.Success> {

        constructor() : this(emptyList())

        override fun mapTo(inputModel: PhotosState.Success): PhotosUiState.Success =
            Success(inputModel.photos.map { PictureInfoUiModel().mapTo(it) })
    }

    class Error(override val errorContainer: UiFailModel) : PhotosUiState(),
        UiState.Error,
        PaginationState.Error<UiFailModel>,
        MapperToPhotosUiState<PhotosState.Fail> {

        override fun mapTo(inputModel: PhotosState.Fail): PhotosUiState.Error =
            Error(inputModel.errorContainer)
    }

    class Exception(): PhotosUiState(), UiState.Exception
}