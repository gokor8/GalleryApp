package com.example.domain.entities.states

import com.example.domain.core.UiFailModel
import com.example.domain.entities.photos.PictureInfoModel

sealed class PhotosState : ReturnState() {
    class Success(val photos: List<PictureInfoModel>) : PhotosState(), ReturnState.Success
    class Exception : PhotosState(), Error
    class Fail(val errorContainer: UiFailModel) : PhotosState(), Error
}
