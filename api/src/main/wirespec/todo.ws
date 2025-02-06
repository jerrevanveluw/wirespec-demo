type TodoDto {
    id: String,
    title: String,
    description: String
}

endpoint GetTodos GET /todos -> {
    200 -> TodoDto[]
}

type NewTodoDto {
    title: String,
    description: String
}

endpoint PostTodo POST NewTodoDto /todos -> {
    200 -> TodoDto
}

endpoint PutTodo PUT NewTodoDto /todos/{id: String} -> {
    200 -> TodoDto
}

endpoint DeleteTodo DELETE /todos/{id: String} -> {
    200 -> TodoDto
}
