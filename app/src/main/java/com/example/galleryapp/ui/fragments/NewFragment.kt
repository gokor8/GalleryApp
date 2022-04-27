package com.example.galleryapp.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentNewBinding
import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
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
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = CustomRecyclerViewAdapter<String>(listOf("","",""))
    }
}