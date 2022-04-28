package com.example.galleryapp.ui.fragments.home

import android.view.View
import androidx.core.widget.NestedScrollView
import com.example.galleryapp.ui.models.ImageHandler
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewFragment : BaseHomeChildFragment<NewFragmentViewModel>(NewFragmentViewModel::class.java) {

    @Inject
    override lateinit var imageHandler: ImageHandler

    override fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            setError(it.errorText, it.errorPicture)
        }
    }

    override fun setListeners() {
        super.setListeners()
        binding.recyclerView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            val dd = oldScrollX - scrollX
            if(oldScrollX - scrollX > 20)
                binding.paginationProgressBar.visibility = View.VISIBLE
        }
    }
}