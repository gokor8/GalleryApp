package com.example.galleryapp.ui.adapters.models

import androidx.fragment.app.Fragment
import com.example.galleryapp.core.factories.FactoryItemsContainer
import com.example.galleryapp.core.factories.LazyFactory
import com.example.galleryapp.ui.fragments.home.NewFragment
import com.example.galleryapp.ui.fragments.home.PopularFragment
import com.example.galleryapp.ui.fragments.home.SearchFragment

class TabFragmentFactoryItemsContainerImpl :
    FactoryItemsContainer.BaseFactoryItemsContainer<TabFragmentFactoryItemsContainerImpl.TabLazyFactoryItem>() {

    override val _listFactoryItems: MutableList<TabLazyFactoryItem> = mutableListOf()

    abstract class TabLazyFactoryItem : LazyFactory.Item<Int, Fragment> {
        override var id: Int = 0
        abstract val title: String
    }

    class TabNewFactoryFragment(override val title: String) : TabLazyFactoryItem() {
        override val entity: Fragment by lazy { NewFragment() }
    }

    class TabPopularFactoryFragment(override val title: String) : TabLazyFactoryItem(),
        LazyFactory.Item<Int, Fragment> {
        override val entity: Fragment by lazy { PopularFragment() }
    }

    class TabSearchFactoryFragment : TabLazyFactoryItem(), LazyFactory.Item<Int, Fragment> {
        override val title: String = ""
        override val entity: Fragment by lazy { SearchFragment() }
    }
}