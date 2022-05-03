package com.example.domain.entities.states

import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.PictureInfoDomainEntity

sealed class PhotosState : ReturnState() {
    class Success(val photos: List<PictureInfoDomainEntity>) : PhotosState(), ReturnState.Success
    class Exception : PhotosState()
    class Fail(val errorContainer: UiFailModel) : PhotosState(), Error
}
