package com.example.domain.entities.states

import com.example.domain.core.ErrorType
import com.example.domain.core.UiException

sealed class ReturnState {
    interface Success
    interface Error
}