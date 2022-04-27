package com.example.galleryapp.core.factories

class LazyFactory<I, R>(private val factoryModels: FactoryModels<I, R>) {

    val typesCount: Int = factoryModels.listFactoryModels.size

    fun create(fragmentId: I): R =
        factoryModels.listFactoryModels.first { it.id == fragmentId }.entity

    interface Item<I, R> {
        val id: I
        val entity: R
    }
}