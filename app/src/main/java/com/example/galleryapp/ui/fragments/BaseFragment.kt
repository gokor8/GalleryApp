package com.example.galleryapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.galleryapp.ui.UiStructure

abstract class BaseFragment<B : ViewBinding, V : ViewModel>(
    private val fillViewModel: Class<V>
) :
    Fragment(), UiStructure {

    protected var _binding: B? = null
    val binding
        get() = _binding!!

    protected var _viewModel: V? = null
    val viewModel
        get() = _viewModel!!

    @CallSuper
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this)[fillViewModel]
        setObservers()
        setListeners()

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onDestroy() {
        _binding = null
        _viewModel = null
        super.onDestroy()
    }
}