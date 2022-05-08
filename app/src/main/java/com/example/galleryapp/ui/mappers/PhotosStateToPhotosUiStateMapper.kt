package com.example.galleryapp.ui.mappers

import com.example.domain.core.HandleFactory
import com.example.domain.entities.states.PhotosState
import com.example.galleryapp.ui.models.states.PhotosUiState

class PhotosStateToPhotosUiStateMapper : HandleFactory<PhotosState, PhotosUiState> {
    override fun handle(e: PhotosState): PhotosUiState =
        when (e) {
            is PhotosState.Success -> PhotosUiState.Success().mapTo(e)
            is PhotosState.Fail -> PhotosUiState.Error().mapTo(e)
            is PhotosState.Exception -> PhotosUiState.Exception()
        }
}