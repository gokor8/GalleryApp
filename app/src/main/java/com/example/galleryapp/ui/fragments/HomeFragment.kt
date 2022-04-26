package com.example.galleryapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
        HomeFragmentViewModel::class.java,
        { inflater, container -> FragmentHomeBinding.inflate(inflater, container, false) }) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.includedToolbar.toolbar.setupWithNavController(navController, appBarConfiguration)

    }

    override fun setObservers() {

    }

    override fun setListeners() {
        binding.includedToolbar.apply {
            editTextSearch.setOnFocusChangeListener { _, _ ->
                val editText = editTextSearch.text
                searchInput.hint = if (editText != null && editText.isEmpty()) {
                    null
                } else {
                    getString(R.string.search)
                }
            }
        }
    }
}