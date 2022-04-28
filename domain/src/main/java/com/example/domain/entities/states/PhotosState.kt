package com.example.domain.entities.states

import com.example.domain.entities.states.AuthState

sealed class PhotosState : ReturnState() {
    class Success(val photos: List<String>) : PhotosState(), ReturnState.Success
    class Error() : PhotosState(), ReturnState.Error
}
