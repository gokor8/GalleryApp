package com.example.galleryapp.core.factories

interface FactoryItemsContainer<M : LazyFactory.Item<*, *>> {

    val listFactoryItems: List<M>

    fun addItem(item: M)

    fun addItems(items: List<M>)

    abstract class BaseFactoryItemsContainer<M : LazyFactory.Item<Int, *>> : FactoryItemsContainer<M> {
        protected abstract val _listFactoryItems: MutableList<M>
        override val listFactoryItems: List<M>
            get() = _listFactoryItems

        override fun addItem(item: M) {
            item.id = _listFactoryItems.size
            _listFactoryItems.add(item)
        }

        override fun addItems(items: List<M>) {
            for (factoryModelIndex in items.indices) {
                val factoryModel = items[factoryModelIndex]

                factoryModel.id = _listFactoryItems.size

                _listFactoryItems.add(factoryModel)
            }
        }
    }
}