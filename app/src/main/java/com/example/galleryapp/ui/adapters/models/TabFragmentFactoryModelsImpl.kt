package com.example.galleryapp.ui.adapters.models

import androidx.fragment.app.Fragment
import com.example.galleryapp.core.factories.FactoryModels
import com.example.galleryapp.core.factories.LazyFactory

class TabFragmentFactoryModelsImpl :
    FactoryModels<TabFragmentFactoryModelsImpl.TabLazyFactoryItem> {

    private val _listFactoryModels: MutableList<TabLazyFactoryItem> = mutableListOf()
    override val listFactoryModels: List<TabLazyFactoryItem> = _listFactoryModels

    override fun addItem(item: TabLazyFactoryItem) {
        _listFactoryModels.add(item)
    }

    interface TabLazyFactoryItem : LazyFactory.Item<Int, Fragment> {
        val title: String
    }

    class TabNewFactoryFragment(override val title: String) :
        BaseFragmentFactoryModelsImpl.NewFactoryFragment(), TabLazyFactoryItem

    class TabPopularFactoryFragment(override val title: String) :
        BaseFragmentFactoryModelsImpl.PopularFactoryFragment(), TabLazyFactoryItem

    class TabSearchFactoryFragment :
        BaseFragmentFactoryModelsImpl.SearchFactoryFragment(), TabLazyFactoryItem {
        override val title: String = ""
    }
}