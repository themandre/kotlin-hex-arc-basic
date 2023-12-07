package com.mandre.kha.users.infrastructure.adapters.input.rest.data.request

data class CreateUserRequest(
    val username: String,
    val password: String,
    val email: String,
    val name: String,
    val lastName: String
)

