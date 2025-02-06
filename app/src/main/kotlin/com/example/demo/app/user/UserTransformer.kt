package com.example.demo.app.user

import com.example.demo.app.common.Transformer
import com.example.demo.domain.user.model.User
import community.flock.wirespec.generated.api.kotlin.model.UserDto
import java.time.LocalDate
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE

object UserTransformer : Transformer<UserDto, User, UserDto> {
    override fun UserDto.consume() =
        User(
            userName = userName,
            birthDate = LocalDate.parse(birthDay, ISO_LOCAL_DATE),
        )

    override fun User.produce() =
        UserDto(
            userName = userName,
            birthDay = birthDate.format(ISO_LOCAL_DATE),
        )
}
