package com.mandre.kha.users.infrastructure.adapters.input.rest

import com.mandre.kha.users.application.ports.input.*
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.request.CreateUserRequest
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.request.UpdateUserRequest
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.response.CreateUserResponse
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.response.GetAllUsersResponse
import com.mandre.kha.users.infrastructure.adapters.input.rest.data.response.GetUserByIdResponse
import com.mandre.kha.users.infrastructure.adapters.input.rest.mapper.UserRestMapper
import com.mandre.kha.users.application.ports.input.CreateUserUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/users")
@Tag(name = "Users", description = "Users API")
class UserController(
    private val createUserUseCase: CreateUserUseCase,
    private val deleteUserUseCase: DeleteUserUseCase,
    private val findAllUsersUseCase: FindAllUsersUseCase,
    private val findOneUserUseCase: FindOneUserUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val userRestMapper: UserRestMapper
) {
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an user", description = "Delete an user from the system")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "204",
                description = "User deleted"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error"
            )
        ]
    )
    suspend fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        deleteUserUseCase.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping
    @Operation(summary = "Get all users", description = "Get all the users in the system")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Users retrieved",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = GetAllUsersResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error"
            )
        ]
    )
    suspend fun getAllUsers(): ResponseEntity<List<GetAllUsersResponse>> {
        val users = findAllUsersUseCase.findAll()
        return ResponseEntity.ok(userRestMapper.toGetAllUserResponse(users))
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find one user", description = "Find one user in the system")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User retrieved",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = GetUserByIdResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "404",
                description = "User not found"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error"
            )
        ]
    )
    suspend fun getUserById(@PathVariable id: Long): ResponseEntity<GetUserByIdResponse> {
        val user = findOneUserUseCase.findOne(id)
        return user?.let { ResponseEntity.ok(userRestMapper.toGetUserByIdResponse(it)) } ?: ResponseEntity.notFound()
            .build()
    }

    @PostMapping
    @Operation(summary = "Create an user", description = "Create an user in the system")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "User created",
                content = [
                    Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = Schema(implementation = CreateUserResponse::class)
                    )
                ]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error"
            )
        ]
    )
    suspend fun createUser(@RequestBody request: CreateUserRequest): ResponseEntity<CreateUserResponse> {
        val user = userRestMapper.toUser(request)
        val createdUser = createUserUseCase.create(user)
        return ResponseEntity.status(HttpStatus.CREATED).body(userRestMapper.toCreateUserResponse(createdUser))
    }

    @PutMapping
    @Operation(summary = "Update an user", description = "Update an user in the system")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "User updated"
            ),
            ApiResponse(
                responseCode = "400",
                description = "Bad Request"
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal Server Error"
            )
        ]
    )
    suspend fun updateUser(@RequestBody request: UpdateUserRequest): ResponseEntity<Unit> {
        val user = userRestMapper.toUser(request)
        val updatedCount = updateUserUseCase.update(user)
        if (updatedCount == 0L) {
            return ResponseEntity.notFound().build()
        }
        return ResponseEntity.ok().build()
    }
}
