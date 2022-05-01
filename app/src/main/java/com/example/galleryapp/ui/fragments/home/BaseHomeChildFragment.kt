package com.example.galleryapp.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentHomeChildBinding
import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.fragments.BaseFragment
import com.example.galleryapp.ui.models.ImageHandler
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

abstract class BaseHomeChildFragment<V : BaseHomeChildViewModel>(
    fillViewModel: Class<V>,
) : BaseFragment<FragmentHomeChildBinding, V>(fillViewModel, { inflater, container ->
    FragmentHomeChildBinding.inflate(inflater, container, false)
}) {

    private val loadList = listOf(null, null, null, null, null, null)

    @Inject
    open lateinit var imageHandler: ImageHandler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPhotos()
    }

    @CallSuper
    override fun setListeners() {
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = CustomRecyclerViewAdapter(loadList, imageHandler)
    }

    override fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            setError()
        }

        viewModel.photosLiveData.observe(viewLifecycleOwner) { picturesInfo ->
            binding.recyclerView.adapter = CustomRecyclerViewAdapter(picturesInfo.map { it.pictureModel.name }, imageHandler)
        }

        viewModel.notifyFailLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it.getMessage(), Snackbar.LENGTH_SHORT).show()
        }
    }

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
}