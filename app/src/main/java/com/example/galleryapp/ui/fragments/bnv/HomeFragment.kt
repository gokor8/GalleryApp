package com.example.galleryapp.ui.fragments.bnv

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.example.galleryapp.R
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.databinding.FragmentHomeBinding
import com.example.galleryapp.ui.adapters.FragmentTabLayoutAdapter
import com.example.galleryapp.ui.adapters.models.BaseFragmentFactoryModelsImpl
import com.example.galleryapp.ui.adapters.models.TabFragmentFactoryModelsImpl
import com.example.galleryapp.ui.fragments.BaseFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding, HomeFragmentViewModel>(
        HomeFragmentViewModel::class.java,
        { inflater, container -> FragmentHomeBinding.inflate(inflater, container, false) }) {

    @Inject
    //lateinit var lazyFactory: LazyFactory<LazyFactory.Item<Int, Fragment>, Int, Fragment>
    lateinit var lazyFactory: LazyFactory<TabFragmentFactoryModelsImpl.TabLazyFactoryItem, Int, Fragment>

    private val arrayLayoutNames = arrayOf("New", "Popular")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.includedToolbar.editTextSearch.clearFocus()
    }

    override fun setObservers() {

    }

    override fun setListeners() {

        lazyFactory.addFactoryItem(TabFragmentFactoryModelsImpl.TabNewFactoryFragment("New"))
        lazyFactory.addFactoryItem(TabFragmentFactoryModelsImpl.TabPopularFactoryFragment("Popular"))
        lazyFactory.addFactoryItem(TabFragmentFactoryModelsImpl.TabSearchFactoryFragment())

        binding.viewPager.adapter =
            FragmentTabLayoutAdapter(lazyFactory, this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            if (lazyFactory.getByIndex(position) is TabFragmentFactoryModelsImpl.TabSearchFactoryFragment) {
                tab.view.visibility = View.GONE
            } else {
                tab.text = lazyFactory.getByIndex(position).title
            }
        }.attach()

        binding.includedToolbar.apply {
            editTextSearch.addTextChangedListener { editableText ->
                //TODO сделать получение фрагмента нормально, не создавая его
                binding.viewPager.setCurrentItem(
                    BaseFragmentFactoryModelsImpl.SearchFactoryFragment().id,
                    true
                )

                editableText?.toString()?.let {
                    viewModel.listener.listenValue = it
                }
            }

            editTextSearch.setOnFocusChangeListener { _, isFocus ->

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