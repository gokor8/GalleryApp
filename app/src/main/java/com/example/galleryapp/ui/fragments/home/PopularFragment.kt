package com.example.galleryapp.ui.fragments.home

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : BaseHomeChildFragment<PopularFragmentViewModel>(
    PopularFragmentViewModel::class.java,
)