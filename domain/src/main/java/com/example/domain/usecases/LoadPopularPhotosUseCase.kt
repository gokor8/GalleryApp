package com.example.domain.usecases

import com.example.domain.core.ServerLoadPhotoTypes
import com.example.domain.repository.MediaRepository

class LoadPopularPhotosUseCase(override val mediaRepository: MediaRepository) : LoadPhotosUseCase {

    override val serverLoadPhotosType: ServerLoadPhotoTypes = ServerLoadPhotoTypes.Popular
}