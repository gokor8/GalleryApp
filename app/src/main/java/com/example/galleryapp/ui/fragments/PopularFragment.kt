package com.example.galleryapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentNewBinding
import com.example.galleryapp.databinding.FragmentPopularBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment: BaseFragment<FragmentPopularBinding, PopularFragmentViewModel>(
    PopularFragmentViewModel::class.java,
    { inflater, container ->
        FragmentPopularBinding.inflate(inflater, container, false)
    }) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun setObservers() {

    }

    override fun setListeners() {

    }
}