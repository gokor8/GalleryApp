package com.example.galleryapp.ui.fragments.home

import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.models.ImageHandler
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : BaseHomeChildFragment<PopularFragmentViewModel>(
    PopularFragmentViewModel::class.java,
)