package com.example.domain.entities

import com.example.domain.core.ValidationTypes

data class ErrorUiEntity(val errorsMap: Map<ValidationTypes, String>) {
}