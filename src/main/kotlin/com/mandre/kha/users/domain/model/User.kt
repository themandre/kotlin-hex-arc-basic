package com.mandre.kha.users.domain.model

data class User(
    var id: Long? = null,
    val username: String,
    var password: String,
    val email: String,
    val name: String,
    val lastName: String
)