package com.example.galleryapp.ui.fragments.home

import android.app.Application
import com.example.galleryapp.ui.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularFragmentViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
}