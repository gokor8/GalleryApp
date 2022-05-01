package com.example.data.core.photos

import com.example.data.api.models.pictures.ResponsePictureInfoModel
import com.example.domain.core.MapperTo
import com.example.domain.entities.states.PhotosState

sealed interface PhotosServerSuccess : MapperTo<PhotosState> {
    class Success(private val pictures: Array<ResponsePictureInfoModel>) : PhotosServerSuccess {
        override fun mapTo(): PhotosState = PhotosState.Success(pictures.map { it.mapTo() })
    }
}