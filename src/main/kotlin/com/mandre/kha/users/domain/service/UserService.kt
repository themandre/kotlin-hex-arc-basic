package com.mandre.kha.users.domain.service

import com.mandre.kha.users.application.ports.input.*
import com.mandre.kha.users.domain.model.User
import com.mandre.kha.users.application.ports.input.CreateUserUseCase

class UserService() : CreateUserUseCase, DeleteUserUseCase, FindAllUsersUseCase, FindOneUserUseCase, UpdateUserUseCase {

    val users = mutableListOf(
        User(1, "pepe", "pepe1234", "pepe@gmail.com", "Pepe", "Morales"),
        User(2, "juan", "juan1234", "juan@gmail.com", "Juan", "Morales")
    )

    override suspend fun create(user: User): User {
        users.lastOrNull()?.let { user.id = it.id?.plus(1) }
        return if (users.add(user)) {
            user
        } else {
            throw Exception("User not created")
        }
    }

    override suspend fun delete(id: Long) = if (users.removeIf { user -> user.id == id }) 1L else 0L

    override suspend fun findAll(): List<User> = users

    override suspend fun findOne(id: Long): User? = users.find { user -> user.id == id }

    override suspend fun update(user: User): Long {
        findOne(user.id!!) ?: return 0L
        users.replaceAll { u -> if (u.id == user.id) user else u }
        return 1L
    }
}