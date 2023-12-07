package com.mandre.kha.users.infrastructure.adapters.input.rest.data.response

data class GetUserByIdResponse(
    val id: Long?,
    val username: String,
    val email: String,
)
