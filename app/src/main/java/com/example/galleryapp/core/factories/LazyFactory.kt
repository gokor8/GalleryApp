package com.example.galleryapp.core.factories

class LazyFactory<M : LazyFactory.Item<I, R>, I, R>(private val factoryModels: FactoryModels<M>) {

    val typesCount: Int
        get() = factoryModels.listFactoryModels.size

    fun create(fragmentId: I): R? {
        for (itemIndex in factoryModels.listFactoryModels.indices) {
            val lazyFactoryItem = factoryModels.listFactoryModels[itemIndex]
            if (lazyFactoryItem.id == fragmentId) return lazyFactoryItem.entity
        }

        return null
    }

    // factoryModels.listFactoryModels.first { it.id == fragmentId }.entity
    fun getByIndex(index: Int) = factoryModels.listFactoryModels[index]

    fun addFactoryItem(item: M) {
        factoryModels.addItem(item)
    }

    interface Item<I, R> {
        val id: I
        val entity: R
    }
}