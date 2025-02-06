package com.example.demo.app.todo.downstream

import com.example.demo.app.common.SymmetricConverter
import com.example.demo.app.common.UUIDConverter.externalize
import com.example.demo.app.common.UUIDConverter.internalize
import com.example.demo.domain.todo.model.Todo
import community.flock.wirespec.generated.spi.kotlin.model.TodoDto

object TodoConverter : SymmetricConverter<TodoDto, Todo> {
    override fun TodoDto.internalize() =
        Todo(
            id = id.internalize(),
            title = title,
            description = description,
        )

    override fun Todo.externalize() =
        TodoDto(
            id = id.externalize(),
            title = title,
            description = description,
        )
}
