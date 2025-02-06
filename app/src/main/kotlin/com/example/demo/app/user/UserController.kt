package com.example.demo.app.user

import com.example.demo.app.common.AppLayer
import com.example.demo.app.common.handle
import com.example.demo.domain.user.HasUserService
import com.example.demo.domain.user.saveUser
import community.flock.wirespec.generated.api.kotlin.model.UserDto
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

interface UserControllerDI : HasUserService

@RestController
@RequestMapping(produces = [APPLICATION_JSON_VALUE])
class UserController(
    appLayer: AppLayer,
) : UserControllerDI by appLayer {
    @PostMapping("/users", consumes = [APPLICATION_JSON_VALUE])
    fun postUser(
        @RequestBody user: UserDto,
    ): UserDto = handle(UserTransformer, user, userService::saveUser)
}
