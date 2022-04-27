package com.example.galleryapp.ui.fragments

import androidx.collection.arrayMapOf
import androidx.fragment.app.Fragment
import com.example.galleryapp.R
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.databinding.FragmentHomeBinding
import com.example.galleryapp.ui.adapters.FragmentTabLayoutAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
        HomeFragmentViewModel::class.java,
        { inflater, container -> FragmentHomeBinding.inflate(inflater, container, false) }) {

    @Inject
    lateinit var lazyFactory: LazyFactory<Int, Fragment>

    private val arrayLayoutNames = arrayOf("New", "Popular")

    override fun setObservers() {

    }

    override fun setListeners() {
        binding.includedToolbar.apply {
            editTextSearch.setOnFocusChangeListener { _, _ ->
                val editText = editTextSearch.text
                searchInput.hint = if (editText != null && editText.isEmpty()) {
                    null
                } else {
                    getString(R.string.search)
                }
            }
        }

        binding.viewPager.adapter = FragmentTabLayoutAdapter(lazyFactory = lazyFactory, fragment = this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = arrayLayoutNames[position]
        }.attach()
    }
}