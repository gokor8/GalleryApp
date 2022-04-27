package com.example.galleryapp.core.factories

interface FactoryModels<I,R> {

    val listFactoryModels: List<LazyFactory.Item<I,R>>
}