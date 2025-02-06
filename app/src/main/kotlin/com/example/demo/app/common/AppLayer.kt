package com.example.demo.app.common

import com.example.demo.app.todo.downstream.LiveTodoAdapter
import com.example.demo.app.todo.web.TodoControllerDI
import com.example.demo.app.user.UserControllerDI
import com.example.demo.domain.todo.TodoService
import com.example.demo.domain.todo.model.Todo
import com.example.demo.domain.user.UserService
import com.example.demo.domain.user.model.User
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.UUID

interface AppLayer :
    UserControllerDI,
    TodoControllerDI

@Component
class LiveAppLayer : AppLayer {
    override val todoService =
        object : TodoService {
            override val todos =
                listOf(
                    Todo(
                        id = UUID.fromString("7d6ceefd-cef3-4f58-b3a1-5cd24539e748"),
                        title = "Some Title",
                        description = "Some Description",
                    ),
                ).associateBy { it.id }.toMutableMap()
        }
    override val userService =
        object : UserService {
            override val users =
                listOf(
                    User(
                        id = UUID.randomUUID(),
                        userName = "SomeUserName",
                        birthDate = LocalDate.of(1990, 1, 1),
                    ),
                ).associateBy { it.id }.toMutableMap()
        }
}

class TodoService2(
    private val todoAdapter: LiveTodoAdapter,
) {
    suspend fun getTodos() = todoAdapter.getTodos()

    suspend fun updateTodo(todo: Todo) = todoAdapter.updateTodo(todo)
}
