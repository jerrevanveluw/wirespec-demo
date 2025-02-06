type TodoDto {
    id: String,
    title: String,
    description: String
}

endpoint GetTodos GET /todos -> {
    200 -> TodoDto[]
}

endpoint PutTodoById PUT TodoDto /todos/{id: String} -> {
    200 -> TodoDto
}
