package com.example.galleryapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.google.android.material.textfield.TextInputLayout

abstract class BaseFragment<B : ViewBinding, V : ViewModel> : Fragment() {

    protected var _binding: B? = null
    protected var _viewModel: V? = null
    val binding: B by lazy { _binding!! }
    val viewModel: V by lazy { _viewModel!! }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setObservers()
        setListeners()
        setMainLogic()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    abstract fun setObservers()

    abstract fun setListeners()

    abstract fun setMainLogic()
}