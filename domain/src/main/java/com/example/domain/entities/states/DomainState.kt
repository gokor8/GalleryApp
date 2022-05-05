package com.example.domain.entities.states

sealed class DomainState {
    interface Success
    interface Error
    interface Exception
}