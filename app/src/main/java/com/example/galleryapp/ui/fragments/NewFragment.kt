package com.example.galleryapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentNewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewFragment : BaseFragment<FragmentNewBinding, NewFragmentViewModel>(
    NewFragmentViewModel::class.java,
    { inflater, container ->
        FragmentNewBinding.inflate(inflater, container, false)
    }) {

    override fun setObservers() {

    }

    override fun setListeners() {

    }
}