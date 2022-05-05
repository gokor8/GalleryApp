package com.example.galleryapp.ui.pagination

import com.example.domain.core.HandleFactory
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PaginationState
import com.example.galleryapp.ui.models.states.PhotosUiState
import java.io.IOException

class PhotosPagination(
    private val loadPhotosUseCase: LoadPhotosUseCase,
    private val handleFactory: HandleFactory<PhotosState, PaginationState>
) : PageSource<PictureInfoUiModel>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PictureInfoUiModel> {
        val page = params.key ?: 1
        val loadSize = params.loadSize

        val willGetPictureInfo = ShowPicturesInfo(page, loadSize)

        val gotPicturesState = loadPhotosUseCase.loadPhotos(willGetPictureInfo)

        val photosUiState = handleFactory.handle(gotPicturesState)

        val nextKey = page + 1
        val prevkey = if (page == 1) null else page - 1

        return when(photosUiState) {
            is PhotosUiState.Success -> LoadResult.Page(photosUiState.paginationDataList, prevkey, nextKey)
            else -> LoadResult.Error(IOException())
        }
    }
}