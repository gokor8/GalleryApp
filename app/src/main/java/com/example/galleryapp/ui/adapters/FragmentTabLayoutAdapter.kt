package com.example.galleryapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.ui.adapters.models.FactoryModelsImpl
import javax.inject.Inject

class FragmentTabLayoutAdapter @Inject constructor(
    private val lazyFactory: LazyFactory<Int, Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = lazyFactory.typesCount

    override fun createFragment(position: Int): Fragment = lazyFactory.create(position)
}