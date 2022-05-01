package com.example.domain.repository

import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState

interface MediaRepository {

    suspend fun loadPictures(showDataInfo: ShowPicturesInfo) : PhotosState
}