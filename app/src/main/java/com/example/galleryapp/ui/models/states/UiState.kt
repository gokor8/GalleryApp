package com.example.galleryapp.ui.models.states

sealed interface UiState {
    interface Success : UiState
    interface Error : UiState
    interface Exception : UiState
}