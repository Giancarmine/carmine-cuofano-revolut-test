package com.example.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDTO(
    val errorMessage: String
)