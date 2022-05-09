package com.example.galleryapp.core.factories

interface FactoryModels<M : LazyFactory.Item<*, *>> {

    val listFactoryModels: List<M>

    fun addItem(item: M)
}