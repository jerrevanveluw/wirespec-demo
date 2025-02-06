package com.example.demo.domain.user

import com.example.demo.domain.user.model.User
import java.util.UUID

interface UserService {
    val users: MutableMap<UUID, User>
}

interface HasUserService {
    val userService: UserService
}

fun UserService.saveUser(user: User) = user.also { users[it.id] = it }
