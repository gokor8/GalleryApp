package com.example.galleryapp.ui.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

open class BaseFragment<B : ViewBinding, V : ViewModel> : Fragment() {

    protected var binding: B? = null
    protected var viewModel: V? = null

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}