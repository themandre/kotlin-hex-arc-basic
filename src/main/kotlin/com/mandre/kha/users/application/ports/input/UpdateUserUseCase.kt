package com.mandre.kha.users.application.ports.input

import com.mandre.kha.users.domain.model.User

interface UpdateUserUseCase {

    suspend fun update(user: User): Long
}