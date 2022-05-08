package com.example.galleryapp.ui.fragments.bnv

import android.app.Application
import com.example.galleryapp.core.ui.Listener
import com.example.galleryapp.ui.fragments.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(
    application: Application,
    val listener: Listener.Save<String>
) :
    BaseViewModel(application) {
}