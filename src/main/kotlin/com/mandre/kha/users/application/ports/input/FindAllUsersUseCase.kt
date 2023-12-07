package com.mandre.kha.users.application.ports.input

import com.mandre.kha.users.domain.model.User

interface FindAllUsersUseCase {
    suspend fun findAll(): List<User>
}