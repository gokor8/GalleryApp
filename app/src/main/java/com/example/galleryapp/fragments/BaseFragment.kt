package com.example.galleryapp.fragments

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<F : ViewBinding> : Fragment() {

    protected var binding: F? = null

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

}