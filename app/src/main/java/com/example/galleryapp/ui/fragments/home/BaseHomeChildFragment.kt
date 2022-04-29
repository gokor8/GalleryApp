package com.example.galleryapp.ui.fragments.home

import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentHomeChildBinding
import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.fragments.BaseFragment
import com.example.galleryapp.ui.fragments.HomeViewModel
import com.example.galleryapp.ui.models.ImageHandler

abstract class BaseHomeChildFragment<V : HomeViewModel>(
    fillViewModel: Class<V>,
) : BaseFragment<FragmentHomeChildBinding, V>(fillViewModel, { inflater, container ->
    FragmentHomeChildBinding.inflate(inflater, container, false)
}) {

    private val loadList = listOf(null, null, null, null, null, null)

    open lateinit var imageHandler: ImageHandler

    fun setError() {
        binding.errorText.text = getString(R.string.error_text)
        binding.errorImage.setImageResource(R.drawable.ic_error_home)
        binding.recyclerView.visibility = View.GONE
        binding.linearLayout.visibility = View.VISIBLE
    }

    fun removeError() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.linearLayout.visibility = View.GONE
    }

    @CallSuper
    override fun setListeners() {
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = CustomRecyclerViewAdapter(loadList, imageHandler)
    }
}