package com.example.galleryapp.ui.fragments.home

import com.example.galleryapp.databinding.FragmentPopularBinding
import com.example.galleryapp.ui.fragments.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment: BaseFragment<FragmentPopularBinding, PopularFragmentViewModel>(
    PopularFragmentViewModel::class.java,
    { inflater, container ->
        FragmentPopularBinding.inflate(inflater, container, false)
    }) {

    override fun setObservers() {

    }

    override fun setListeners() {

    }
}