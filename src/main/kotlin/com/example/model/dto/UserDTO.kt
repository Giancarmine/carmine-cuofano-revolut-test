package com.example.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDTO(
    val username: String,
    val dateOfBirth: String
)