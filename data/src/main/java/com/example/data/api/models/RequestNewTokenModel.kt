package com.example.data.api.models

data class RequestNewTokenModel(val name: String, val allowedGrantTypes: List<String>) {
}