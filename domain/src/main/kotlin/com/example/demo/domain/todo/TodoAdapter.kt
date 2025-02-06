package com.example.demo.domain.todo

import com.example.demo.domain.todo.model.Todo

interface TodoAdapter {
    suspend fun getTodos(): List<Todo>

    suspend fun updateTodo(todo: Todo): Todo
}
