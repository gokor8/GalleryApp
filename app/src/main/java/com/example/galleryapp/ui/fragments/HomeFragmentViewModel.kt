package com.example.galleryapp.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(application: Application) :
    BaseViewModel(application) {

}