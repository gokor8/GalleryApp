package com.example.galleryapp.ui.fragments.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.recyclerview.widget.GridLayoutManager
import com.example.galleryapp.databinding.FragmentHomeChildBinding
import com.example.galleryapp.ui.adapters.CustomRecyclerViewAdapter
import com.example.galleryapp.ui.fragments.BaseFragment

class BaseHomeChildFragment<V : BaseHomeChildrenViewModel>(
    fillViewModel: Class<V>,
    bindingFragment: (inflater: LayoutInflater, container: ViewGroup?) -> FragmentHomeChildBinding
) : BaseFragment<FragmentHomeChildBinding, V>(fillViewModel, bindingFragment) {

    @CallSuper
    override fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            binding.errorText.text = getString(it.errorText)
            binding.errorImage.setImageResource(it.errorPicture)
            binding.recyclerView.visibility = View.GONE
            binding.linearLayout.visibility = View.VISIBLE
        }
    }

    @CallSuper
    override fun setListeners() {
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = CustomRecyclerViewAdapter<String>(listOf("","",""))
    }
}