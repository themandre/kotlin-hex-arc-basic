package com.mandre.kha.users.application.ports.input

import com.mandre.kha.users.domain.model.User

interface FindOneUserUseCase {
    suspend fun findOne(id: Long): User?

}