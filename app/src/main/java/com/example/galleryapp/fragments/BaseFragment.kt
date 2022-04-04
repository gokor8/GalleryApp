package com.example.galleryapp.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

open class BaseFragment<F : ViewBinding, V : ViewModel> : Fragment() {

    protected var binding: F? = null
    protected var viewModel: V? = null

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}