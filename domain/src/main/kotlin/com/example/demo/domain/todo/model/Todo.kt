package com.example.demo.domain.todo.model

import java.util.UUID

data class Todo(
    val id: UUID = UUID.randomUUID(),
    val title: String,
    val description: String,
)
