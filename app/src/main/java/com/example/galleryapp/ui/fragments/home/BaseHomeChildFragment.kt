package com.example.galleryapp.ui.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.core.ui.adapters.BaseViewHolder
import com.example.galleryapp.databinding.FragmentHomeChildBinding
import com.example.galleryapp.databinding.RecyclerViewItemBinding
import com.example.galleryapp.ui.adapters.CustomPhotosViewHolder
import com.example.galleryapp.ui.adapters.BaseRecyclerViewAdapter
import com.example.galleryapp.ui.adapters.PaginationScrollListener
import com.example.galleryapp.ui.adapters.PhotosPaginationScrollListener
import com.example.galleryapp.ui.fragments.BaseFragment
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseHomeChildFragment<V : BaseHomeChildViewModel>(
    fillViewModel: Class<V>,
) : BaseFragment<FragmentHomeChildBinding, V>(fillViewModel, { inflater, container ->
    FragmentHomeChildBinding.inflate(inflater, container, false)
}) {

    private var isLoading = false

    private val layoutManager by lazy { GridLayoutManager(this.context, 2) }

    private val pagingAdapter by lazy {
        BaseRecyclerViewAdapter<PictureInfoUiModel, BaseViewHolder<PictureInfoUiModel>>(
            listOf(null, null, null, null, null, null)
        ) { parent ->
            val binding =
                RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            CustomPhotosViewHolder(binding)
        }
    }

    @CallSuper
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = pagingAdapter

        viewModel.loadPhotos()
    }

    @CallSuper
    override fun setListeners() {
        binding.recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun loadMoreItems() {
                viewModel.page++
                viewModel.loadPhotos()
            }

            override val isLoading: Boolean
                get() = this@BaseHomeChildFragment.isLoading
        })
    }

    @CallSuper
    override fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            setError()
        }

        viewModel.photosLiveData.observe(viewLifecycleOwner) {
            isLoading = false
        }

        viewModel.notifyFailLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it.getMessage(), Snackbar.LENGTH_SHORT).show()
            isLoading = false
        }
    }

    protected fun setError() {
        binding.errorTitle.text = getString(R.string.error_title)
        binding.errorText.text = getString(R.string.error_text)
        binding.errorImage.setImageResource(R.drawable.ic_error_home)
        binding.recyclerView.visibility = View.GONE
        binding.linearLayout.visibility = View.VISIBLE
    }

    protected fun removeError() {
        binding.recyclerView.visibility = View.VISIBLE
        binding.linearLayout.visibility = View.GONE
    }
}