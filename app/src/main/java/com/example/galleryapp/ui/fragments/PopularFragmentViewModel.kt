package com.example.galleryapp.ui.fragments

import android.app.Application
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopularFragmentViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
}