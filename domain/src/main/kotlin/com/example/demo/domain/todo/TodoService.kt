package com.example.demo.domain.todo

import com.example.demo.domain.todo.model.Todo
import java.util.UUID
import kotlin.collections.set

interface TodoService {
    val todos: MutableMap<UUID, Todo>
}

interface HasTodoService {
    val todoService: TodoService
}

fun TodoService.getAllTodos(): List<Todo> = todos.values.toList()

fun TodoService.saveTodo(todo: Todo): Todo = todo.also { todos[it.id] = it }

fun TodoService.updateTodoById(
    id: UUID,
    todo: Todo,
): Todo? =
    todos[id]
        ?.also { todos[it.id] = todo }
        ?.let { todo }

fun TodoService.deleteTodoById(id: UUID): Todo? = todos.remove(id)
