package com.example.galleryapp.ui.adapters.models

import androidx.fragment.app.Fragment
import com.example.galleryapp.ui.fragments.home.NewFragment
import com.example.galleryapp.ui.fragments.home.PopularFragment
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.core.factories.FactoryModels

class FragmentFactoryModelsImpl : FactoryModels<Int, Fragment> {

    override val listFactoryModels = listOf(NewFactoryFragment(), PopularFactoryFragment())

    private class NewFactoryFragment : LazyFactory.Item<Int, Fragment> {
        override val id: Int = 0
        override val entity: Fragment by lazy { NewFragment() }
    }

    private class PopularFactoryFragment : LazyFactory.Item<Int, Fragment> {
        override val id: Int = 1
        override val entity: Fragment by lazy { PopularFragment() }
    }
}