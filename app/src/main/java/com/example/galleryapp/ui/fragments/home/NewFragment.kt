package com.example.galleryapp.ui.fragments.home

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewFragment : BaseHomeChildFragment<NewFragmentViewModel>(NewFragmentViewModel::class.java)