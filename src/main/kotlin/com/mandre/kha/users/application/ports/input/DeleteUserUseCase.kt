package com.mandre.kha.users.application.ports.input

interface DeleteUserUseCase {

    suspend fun delete(id: Long): Long

}