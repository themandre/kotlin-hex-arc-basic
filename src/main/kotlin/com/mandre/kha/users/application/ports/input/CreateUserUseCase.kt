package com.mandre.kha.users.application.ports.input

import com.mandre.kha.users.domain.model.User

interface CreateUserUseCase {

    suspend fun create(user: User): User

}