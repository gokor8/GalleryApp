package com.example.data.core.photos

import com.example.domain.core.HandleFactory
import com.example.domain.core.MapperTo
import com.example.domain.entities.states.PhotosState

sealed class PhotosServerFail(protected val mapFactory: HandleFactory<PhotosServerFail>) :
    MapperTo<PhotosState> {

    class NoData(mapFactory: HandleFactory<PhotosServerFail>) : PhotosServerFail(mapFactory) {
        override fun mapTo(): PhotosState = PhotosState.Fail(mapFactory.handle(this))
    }
}