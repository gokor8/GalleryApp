package com.example.galleryapp.ui.pagination

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.core.HandleFactory
import com.example.domain.entities.photos.ShowPicturesInfo
import com.example.domain.entities.states.PhotosState
import com.example.domain.usecases.LoadPhotosUseCase
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.example.galleryapp.ui.models.states.PaginationState
import com.example.galleryapp.ui.models.states.PhotosUiState
import okhttp3.internal.userAgent
import java.io.IOException

// Сделать класс PaginationState, который обязательно возвращает успешное состояние с листом.
// Так же сделать в PagginationState проброс ошибочного состояния и експешн состояния
// Сделать интерфейс UseCase, чтобы мог ограничивать сюда только usecaseы
abstract class PageSource<M : Any>: PagingSource<Int, M>() {

    override fun getRefreshKey(state: PagingState<Int, M>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}