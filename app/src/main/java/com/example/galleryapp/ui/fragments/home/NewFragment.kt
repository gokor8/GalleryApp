package com.example.galleryapp.ui.fragments.home

import androidx.annotation.CallSuper
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.databinding.FragmentNewBinding
import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.fragments.BaseFragment
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