package com.example.galleryapp.ui.fragments.home

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.R
import com.example.galleryapp.databinding.FragmentHomeChildBinding
import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.fragments.BaseFragment
import com.example.galleryapp.ui.models.photo.PictureInfoUiModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

abstract class BaseHomeChildFragment<V : BaseHomeChildViewModel>(
    fillViewModel: Class<V>,
) : BaseFragment<FragmentHomeChildBinding, V>(fillViewModel, { inflater, container ->
    FragmentHomeChildBinding.inflate(inflater, container, false)
}) {

    private val pagingAdapter by lazy {
        CustomRecyclerViewAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = pagingAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.photosFlow.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }

    @CallSuper
    override fun setListeners() {
    }

    @CallSuper
    override fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            setError()
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