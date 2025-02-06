package com.example.demo.domain.user.model

import java.time.LocalDate
import java.util.UUID

data class User(
    val id: UUID = UUID.randomUUID(),
    val userName: String,
    val birthDate: LocalDate,
)
