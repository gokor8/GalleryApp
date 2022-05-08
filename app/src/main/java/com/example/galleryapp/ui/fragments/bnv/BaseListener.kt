package com.example.galleryapp.ui.fragments.bnv

import com.example.galleryapp.core.ui.Listener

class BaseListener<V : Any> : Listener.Save<V>, Listener.Read<V> {

    interface Subscriber<E> {
        val subscribeEntity : E

        fun observe()
    }

    private val subscribesList = mutableListOf<Subscriber<*>>()

    override var listenValue: V? = null
    set(value) {
        value?.let {
            field = value
            for (subscriber in subscribesList) subscriber.observe()
        }
    }

    override fun<E> observe(entity: E, value: (V) -> Unit) {
        object : Subscriber<E> {
            override fun observe() {
                listenValue?.let(value)
            }

            override val subscribeEntity: E = entity
        }.let(subscribesList::add)
    }

    override fun<E> unsubscribe(entity: E) {
        subscribesList.removeIf { it.subscribeEntity == entity }
    }
}