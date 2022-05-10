package com.example.galleryapp.ui.fragments.bnv

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.galleryapp.R
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.databinding.FragmentHomeBinding
import com.example.galleryapp.ui.adapters.FragmentTabLayoutAdapter
import com.example.galleryapp.ui.adapters.models.TabFragmentFactoryItemsContainerImpl
import com.example.galleryapp.ui.fragments.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
        HomeFragmentViewModel::class.java,
        { inflater, container -> FragmentHomeBinding.inflate(inflater, container, false) }) {

    @Inject
    lateinit var lazyFactory: LazyFactory<TabFragmentFactoryItemsContainerImpl.TabLazyFactoryItem, Int, Fragment>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includedToolbar.editTextSearch.clearFocus()
        binding.includedToolbar.searchInput.clearFocus()
    }

    override fun setInit() {
        lazyFactory.addFactoryItems(
            listOf(
                TabFragmentFactoryItemsContainerImpl.TabNewFactoryFragment("New"),
                TabFragmentFactoryItemsContainerImpl.TabPopularFactoryFragment("Popular"),
                TabFragmentFactoryItemsContainerImpl.TabSearchFactoryFragment()
            )
        )
    }

    override fun setObservers() {

    }

    override fun setListeners() {
        binding.viewPager.adapter =
            FragmentTabLayoutAdapter(lazyFactory, this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (lazyFactory.getByIndex(position) is TabFragmentFactoryItemsContainerImpl.TabSearchFactoryFragment) {
                tab.view.visibility = View.GONE
                // Могу вынести в классы фабрики, с интерфейсами и инкапсуляцией, но лень
            } else {
                tab.text = lazyFactory.getByIndex(position).title
            }
        }.attach()

        binding.includedToolbar.apply {
            editTextSearch.addTextChangedListener { editableText ->

                binding.viewPager.setCurrentItem(
                    lazyFactory.getBy { it is TabFragmentFactoryItemsContainerImpl.TabSearchFactoryFragment }.id,
                    true
                )

                editableText?.toString()?.let {
                    viewModel.listener.listenValue = it
                }
            }

            editTextSearch.setOnFocusChangeListener { _, _ ->

                val editText = editTextSearch.text
                searchInput.hint = if (editText != null && editText.isEmpty()) {
                    null
                } else {
                    getString(R.string.search)
                }
            }
        }
    }
}