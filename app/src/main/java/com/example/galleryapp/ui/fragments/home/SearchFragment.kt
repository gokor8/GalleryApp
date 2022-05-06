package com.example.galleryapp.ui.fragments.home

import android.os.Bundle
import android.view.View
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment() : BaseHomeChildFragment<SearchFragmentViewModel>(SearchFragmentViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setListeners() {
        super.setListeners()

        viewModel.searchPhotos(binding.)
    }
}