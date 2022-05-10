package com.example.galleryapp.core.factories

class LazyFactory<M : LazyFactory.Item<I, R>, I, R>(private val factoryItemsContainer: FactoryItemsContainer<M>) {

    val typesCount: Int
        get() = factoryItemsContainer.listFactoryItems.size

    fun create(fragmentId: I): R =
        factoryItemsContainer.listFactoryItems.first { it.id == fragmentId }.entity
    /*for (itemIndex in factoryItemsContainer.listFactoryItems.indices) {
        val lazyFactoryItem = factoryItemsContainer.listFactoryItems[itemIndex]
        if (lazyFactoryItem.id == fragmentId) return lazyFactoryItem.entity
    }
    throw Exception("Havent fragment with this id. May be null")*/

    fun getByIndex(index: Int) = factoryItemsContainer.listFactoryItems[index]

    fun getBy(expression: (item: M) -> Boolean): M =
        factoryItemsContainer.listFactoryItems.first { expression(it) }

    fun addFactoryItem(item: M) = factoryItemsContainer.addItem(item)

    fun addFactoryItems(items: List<M>) = factoryItemsContainer.addItems(items)

    interface Item<I, R> {
        var id: I
        val entity: R
    }
}