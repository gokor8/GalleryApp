package com.example.galleryapp.ui.pagination

import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.ui.fragments.home.BaseHomeChildViewModel

class PhotosPaginationScrollListener<VM : BaseHomeChildViewModel>(
    override val isLoading: Boolean,
    private val viewModel: VM,
    layoutManager: GridLayoutManager
) :
    PaginationScrollListener(layoutManager) {
    override fun loadMoreItems() {
        viewModel.page++
        viewModel.loadPhotos()
    }
}