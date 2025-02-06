package com.example.demo.app.todo.web

import com.example.demo.app.common.AppLayer
import com.example.demo.app.common.UUIDTransformer.consume
import com.example.demo.app.todo.web.TodoTransformer.consume
import com.example.demo.app.todo.web.TodoTransformer.produce
import com.example.demo.domain.todo.HasTodoService
import com.example.demo.domain.todo.deleteTodoById
import com.example.demo.domain.todo.getAllTodos
import com.example.demo.domain.todo.saveTodo
import com.example.demo.domain.todo.updateTodoById
import community.flock.wirespec.generated.api.kotlin.endpoint.DeleteTodo
import community.flock.wirespec.generated.api.kotlin.endpoint.GetTodos
import community.flock.wirespec.generated.api.kotlin.endpoint.PostTodo
import community.flock.wirespec.generated.api.kotlin.endpoint.PutTodo
import org.springframework.web.bind.annotation.RestController

interface TodoControllerDI : HasTodoService

interface TodoApi :
    GetTodos.Handler,
    PostTodo.Handler,
    PutTodo.Handler,
    DeleteTodo.Handler

@RestController
class TodoController(
    appLayer: AppLayer,
) : TodoApi,
    TodoControllerDI by appLayer {
    override suspend fun getTodos(request: GetTodos.Request): GetTodos.Response<*> {
        val todos = todoService.getAllTodos()
        val todoDtos = todos.map { it.produce() }
        return GetTodos.Response200(body = todoDtos)
    }

    override suspend fun postTodo(request: PostTodo.Request): PostTodo.Response<*> {
        val potentialTodo = request.body
        val todo = potentialTodo.consume()
        val savedTodo = todoService.saveTodo(todo)
        val todoTdo = savedTodo.produce()
        return PostTodo.Response200(body = todoTdo)
    }

    override suspend fun putTodo(request: PutTodo.Request): PutTodo.Response<*> {
        val potentialId = request.path.id
        val id = potentialId.consume()
        val potentialTodo = request.body
        val todo = potentialTodo.consume()
        val maybeUpdatedTodo = todoService.updateTodoById(id, todo)
        val todoDto = maybeUpdatedTodo?.produce() ?: throw RuntimeException("Todo not found")
        return PutTodo.Response200(body = todoDto)
    }

    override suspend fun deleteTodo(request: DeleteTodo.Request): DeleteTodo.Response<*> {
        val potentialId = request.path.id
        val id = potentialId.consume()
        val maybeUpdatedTodo = todoService.deleteTodoById(id)
        val todoDto = maybeUpdatedTodo?.produce() ?: throw RuntimeException("Todo not found")
        return DeleteTodo.Response200(body = todoDto)
    }
}
