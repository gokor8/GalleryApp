package com.example.galleryapp.ui.adapters

import com.example.galleryapp.core.factories.FactoryItemsContainer
import com.example.galleryapp.core.factories.LazyFactory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class HomeLazyFactoryTest {

    private val fragmentFactoryModels = MockLazyFactoryItemsContainer()
    private val factory = LazyFactory(fragmentFactoryModels)

    @ParameterizedTest
    @ValueSource(ints = [0, 1])
    fun get_fragment_by_id_is_not_empty(id: Int) {
        assert(factory.create(id))
    }

    @ParameterizedTest
    @ValueSource(ints = [2, 3])
    fun get_fragment_by_id_is_empty(id: Int) {
        assert(factory.create(id))
    }

    class MockLazyFactoryItemsContainer() : FactoryItemsContainer<Int, Boolean> {
        val listFactoryItems: List<LazyFactory.Item<Int, Boolean>> = listOf(
            BaseLazyFactoryItem(0), BaseLazyFactoryItem(1)
        )

        class BaseLazyFactoryItem(override val id: Int) : LazyFactory.Item<Int, Boolean> {
            override val entity: Boolean = true
        }
    }
}