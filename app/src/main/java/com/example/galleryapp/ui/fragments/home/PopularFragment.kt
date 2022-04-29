package com.example.galleryapp.ui.fragments.home

import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.models.ImageHandler
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PopularFragment : BaseHomeChildFragment<PopularFragmentViewModel>(
    PopularFragmentViewModel::class.java,
) {

    @Inject
    override lateinit var imageHandler: ImageHandler

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

    override fun setListeners() {
        super.setListeners()
        viewModel.loadPhotos()
    }
}