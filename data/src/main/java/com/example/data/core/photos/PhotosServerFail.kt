package com.example.data.core.photos

import com.example.domain.core.HandleFactory
import com.example.domain.core.MapperTo
import com.example.domain.core.UiFailModel
import com.example.domain.entities.states.PhotosState

sealed class PhotosServerFail(protected val mapFactory: HandleFactory<PhotosServerFail, UiFailModel>) :
    MapperTo<PhotosState> {

    class NoData(mapFactory: HandleFactory<PhotosServerFail, UiFailModel>) : PhotosServerFail(mapFactory) {
        override fun mapTo(): PhotosState = PhotosState.Fail(mapFactory.handle(this))
    }
}