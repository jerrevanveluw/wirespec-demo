package com.example.demo.app.todo.downstream

import com.example.demo.app.todo.downstream.TodoConverter.externalize
import com.example.demo.app.todo.downstream.TodoConverter.internalize
import com.example.demo.domain.todo.TodoAdapter
import com.example.demo.domain.todo.model.Todo
import community.flock.wirespec.generated.spi.kotlin.endpoint.GetTodos
import community.flock.wirespec.generated.spi.kotlin.endpoint.PutTodoById
import org.springframework.stereotype.Component

@Component
class LiveTodoAdapter(
    private val todoClient: TodoClient,
) : TodoAdapter {
    override suspend fun getTodos(): List<Todo> {
        val req = GetTodos.Request
        return when (val res = todoClient.getTodos(req)) {
            is GetTodos.Response200 -> res.body.map { it.internalize() }
        }
    }

    override suspend fun updateTodo(todo: Todo): Todo {
        val req = PutTodoById.Request(todo.id.toString(), todo.externalize())
        return when (val res = todoClient.putTodoById(req)) {
            is PutTodoById.Response200 -> res.body.internalize()
        }
    }
}
