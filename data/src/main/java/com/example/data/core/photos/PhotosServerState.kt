package com.example.data.core.photos

import com.example.data.api.models.pictures.ResponsePictureInfoModel
import com.example.domain.core.HandleFactory
import com.example.domain.core.MapperTo
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.PhotosState

sealed class PhotosServerState : MapperTo<PhotosState> {

    class NoData(protected val mapFactory: HandleFactory<PhotosServerState, UiFailModel>) : PhotosServerState() {
        override fun mapTo(): PhotosState = PhotosState.Fail(mapFactory.handle(this))
    }

    class Success(private val pictures: Array<ResponsePictureInfoModel>) : PhotosServerState() {
        override fun mapTo(): PhotosState = PhotosState.Success(pictures.map { it.mapTo() })
    }
}