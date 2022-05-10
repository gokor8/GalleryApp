package com.example.galleryapp.ui.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState

// Сделать класс PaginationState, который обязательно возвращает успешное состояние с листом.
// Так же сделать в PagginationState проброс ошибочного состояния и експешн состояния
// Сделать интерфейс UseCase, чтобы мог ограничивать сюда только usecaseы
abstract class PageSource<M : Any> : PagingSource<Int, M>() {

    override fun getRefreshKey(state: PagingState<Int, M>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}