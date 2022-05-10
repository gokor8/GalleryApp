package com.example.domain.core.handle_factories

class ListModelHandleFactory<LM : ListModelHandleFactory.SearchTypeModel<I>, I : Any>(private val list: List<LM>) :
    HandleFactory<I, LM> {

    interface SearchTypeModel<I : Any> {

        fun isTriggered(inputData: I): Boolean
    }

    override fun handle(e: I): LM =
        list.first { it.isTriggered(e) }
}