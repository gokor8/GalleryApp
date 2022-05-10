package com.example.galleryapp.ui.models.search_types

import androidx.core.text.isDigitsOnly
import com.example.domain.core.handle_factories.ListModelHandleFactory
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PhotosUiState

sealed interface SearchPhotosModel : ListModelHandleFactory.SearchTypeModel<String> {

    fun checkExpression(
        photosUiSuccessState: PhotosUiState.Success,
        inputData: String
    ): List<PictureInfoUiModel>

    class SearchByIdModel() : SearchPhotosModel {
        override fun isTriggered(inputData: String): Boolean = inputData.isDigitsOnly()

        override fun checkExpression(
            photosUiSuccessState: PhotosUiState.Success,
            inputData: String
        ): List<PictureInfoUiModel> =
            photosUiSuccessState.paginationDataList.filter { it.id == inputData.toInt() }
    }

    class SearchByNameModel() : SearchPhotosModel {
        override fun isTriggered(inputData: String): Boolean = true

        override fun checkExpression(
            photosUiSuccessState: PhotosUiState.Success,
            inputData: String
        ): List<PictureInfoUiModel> =
            photosUiSuccessState.paginationDataList.filter { it.name == inputData }
    }
}