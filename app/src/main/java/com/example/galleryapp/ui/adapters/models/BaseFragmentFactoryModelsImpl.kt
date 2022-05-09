package com.example.galleryapp.ui.adapters.models

import androidx.fragment.app.Fragment
import com.example.galleryapp.ui.fragments.home.NewFragment
import com.example.galleryapp.ui.fragments.home.PopularFragment
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.ui.fragments.home.SearchFragment

class BaseFragmentFactoryModelsImpl : FactoryModels<LazyFactory.Item<Int, Fragment>> {

    private val _listFactoryModels: MutableList<LazyFactory.Item<Int, Fragment>> =
        mutableListOf(NewFactoryFragment(), PopularFactoryFragment(), SearchFactoryFragment())

    override val listFactoryModels = _listFactoryModels

    override fun addItem(item: LazyFactory.Item<Int, Fragment>) {
        _listFactoryModels.add(item)
    }

    open class NewFactoryFragment : LazyFactory.Item<Int, Fragment> {
        override val id: Int = 0
        override val entity: Fragment by lazy { NewFragment() }
    }

    open class PopularFactoryFragment : LazyFactory.Item<Int, Fragment> {
        override val id: Int = 1
        override val entity: Fragment by lazy { PopularFragment() }
    }

    open class SearchFactoryFragment : LazyFactory.Item<Int, Fragment> {
        override val id: Int = 2
        override val entity: Fragment by lazy { SearchFragment() }
    }
}