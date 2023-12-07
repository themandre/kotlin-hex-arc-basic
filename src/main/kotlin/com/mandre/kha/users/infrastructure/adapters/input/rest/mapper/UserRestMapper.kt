package com.mandre.kha.users.infrastructure.adapters.input.rest.mapper

import com.mandre.kha.users.domain.model.User
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.request.CreateUserRequest
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.request.UpdateUserRequest
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.response.CreateUserResponse
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.response.GetAllUsersResponse
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.response.GetUserByIdResponse
import org.springframework.stereotype.Component

@Component
class UserRestMapper {
    fun toUser(createUserRequest: CreateUserRequest): User = User(
        username = createUserRequest.username,
        password = createUserRequest.password,
        email = createUserRequest.email,
        name = createUserRequest.name,
        lastName = createUserRequest.lastName
    )

    fun toUser(updateUserRequest: UpdateUserRequest): User = User(
        id = updateUserRequest.id,
        username = updateUserRequest.username,
        password = updateUserRequest.password,
        email = updateUserRequest.email,
        name = updateUserRequest.name,
        lastName = updateUserRequest.lastName
    )

    fun toCreateUserResponse(user: User) = CreateUserResponse(
        id = user.id,
        username = user.username,
        email = user.email,
    )

    fun toGetAllUserResponse(users: List<User>) = users.map { user ->
        GetAllUsersResponse(
            id = user.id,
            username = user.username,
            email = user.email,
        )
    }

    fun toGetUserByIdResponse(user: User) = GetUserByIdResponse(
        id = user.id,
        username = user.username,
        email = user.email,
    )
}