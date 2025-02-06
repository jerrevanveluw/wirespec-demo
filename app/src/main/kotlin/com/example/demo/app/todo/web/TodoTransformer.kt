package com.example.demo.app.todo.web

import com.example.demo.app.common.Transformer
import com.example.demo.app.common.UUIDTransformer.produce
import com.example.demo.domain.todo.model.Todo
import community.flock.wirespec.generated.api.kotlin.model.NewTodoDto
import community.flock.wirespec.generated.api.kotlin.model.TodoDto
import java.util.UUID

object TodoTransformer : Transformer<NewTodoDto, Todo, TodoDto> {
    override fun NewTodoDto.consume() =
        Todo(
            id = UUID.randomUUID(),
            title = title,
            description = description,
        )

    override fun Todo.produce() =
        TodoDto(
            id = id.produce(),
            title = title,
            description = description,
        )
}
