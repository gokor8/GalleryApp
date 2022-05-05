package com.example.domain.entities.states

import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.PictureInfoDomainEntity

sealed class PhotosState : DomainState() {
    class Success(val photos: List<PictureInfoDomainEntity>) : PhotosState(), DomainState.Success
    class Fail(val errorContainer: UiFailModel) : PhotosState(), DomainState.Error
    class Exception : PhotosState(), DomainState.Exception
}
