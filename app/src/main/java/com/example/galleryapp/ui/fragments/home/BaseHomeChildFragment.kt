package com.example.galleryapp.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentHomeChildBinding
import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.fragments.BaseFragment
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.google.android.material.snackbar.Snackbar

abstract class BaseHomeChildFragment<V : BaseHomeChildViewModel>(
    fillViewModel: Class<V>,
) : BaseFragment<FragmentHomeChildBinding, V>(fillViewModel, { inflater, container ->
    FragmentHomeChildBinding.inflate(inflater, container, false)
}) {

    private val loadList = listOf(null, null, null, null, null, null)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadPhotos()
    }

    @CallSuper
    override fun setListeners() {
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = CustomRecyclerViewAdapter(loadList)
    }

    @CallSuper
    override fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            setError()
        }

        viewModel.photosLiveData.observe(viewLifecycleOwner) { picturesInfo ->
            binding.recyclerView.adapter =
                CustomRecyclerViewAdapter(picturesInfo.map { PictureInfoUiModel().mapTo(it) } )
            removeError()
        }

        viewModel.notifyFailLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(binding.root, it.getMessage(), Snackbar.LENGTH_SHORT).show()
        }
    }

    protected fun setError() {
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