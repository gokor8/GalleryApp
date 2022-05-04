package com.example.galleryapp.ui.models.states

import com.example.domain.core.UiFailModel

sealed interface PaginationState {
    interface Success<T> : PaginationState{
        val paginationDataList: List<T>
    }

    interface Error<T> : PaginationState {
        val errorContainer: T
    }
}