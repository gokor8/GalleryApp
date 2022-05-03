package com.example.galleryapp.ui.paggination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.usecases.LoadPhotosUseCase

// Сделать класс PaginationState, который обязательно возвращает успешное состояние с листом.
// Так же сделать в PagginationState проброс ошибочного состояния и експешн состояния
// Сделать интерфейс UseCase, чтобы мог ограничивать сюда только usecaseы
class PageSource<T : Any>(private val loadPhotosUseCase: LoadPhotosUseCase) :
    PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult
    }
}