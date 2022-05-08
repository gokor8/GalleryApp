package com.example.galleryapp.ui.fragments.home

import android.os.Bundle
import android.view.View
import com.example.galleryapp.core.ui.Listener
import com.example.galleryapp.ui.fragments.bnv.BaseListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class SearchFragment() :
    BaseHomeChildFragment<SearchFragmentViewModel>(SearchFragmentViewModel::class.java) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setListeners() {
        super.setListeners()

        viewModel.listener.observe(this) {
            viewModel.searchPhotos(it.toInt())
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        viewModel.listener.unsubscribe(this)
    }
}