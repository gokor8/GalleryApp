package com.example.galleryapp.ui.models.states

import com.example.domain.core.MapperFrom
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.PhotosState
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel

sealed class PhotosUiState : UiState {

    class Success(override val paginationDataList: List<PictureInfoUiModel>) :
        PhotosUiState(),
        UiState.Success,
        PaginationState.Success<PictureInfoUiModel>,
        MapperFrom<PhotosState.Success, PhotosUiState> {

        constructor() : this(emptyList())

        override fun mapTo(inputModel: PhotosState.Success): PhotosUiState =
            Success(inputModel.photos.map { PictureInfoUiModel().mapTo(it) })
    }

    class Error(override val errorContainer: UiFailModel) :
        PhotosUiState(),
        UiState.Error,
        PaginationState.Error<UiFailModel>,
        MapperFrom<PhotosState.Fail, PhotosUiState> {

        constructor() : this(object:UiFailModel {
            override fun getMessageResId(): Int = 0
            override fun getMessage(): String = ""
        })

        override fun mapTo(inputModel: PhotosState.Fail): PhotosUiState =
            Error(inputModel.errorContainer)
    }

    class Exception() : PhotosUiState(), UiState.Exception,
        MapperFrom<PhotosState.Exception, PhotosUiState> {
        override fun mapTo(inputModel: PhotosState.Exception): PhotosUiState = Exception()
    }
}