package com.example.galleryapp.ui.fragments.home

import com.example.galleryapp.ui.models.ImageHandler
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
            setError(it.errorText, it.errorPicture)
        }
    }

    override fun setListeners() {
        super.setListeners()
    }
}