package com.example.domain.repository

import com.example.domain.core.LoadPhotoTypes
import com.example.domain.entities.states.PhotosState

interface MediaRepository {

    fun loadPictures(loadPhotoTypes: LoadPhotoTypes) : PhotosState
}