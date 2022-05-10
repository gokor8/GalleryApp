package com.example.galleryapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.galleryapp.core.factories.LazyFactory
import javax.inject.Inject

class FragmentTabLayoutAdapter<M : LazyFactory.Item<Int, Fragment>> constructor(
    private val lazyFactory: LazyFactory<M, Int, Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = lazyFactory.typesCount

    override fun createFragment(position: Int): Fragment =
        lazyFactory.create(position)
}