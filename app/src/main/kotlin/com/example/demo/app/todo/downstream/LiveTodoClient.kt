package com.example.demo.app.todo.downstream

import com.example.demo.app.common.wirespec.CustomWirespecWebClient
import community.flock.wirespec.generated.spi.kotlin.endpoint.GetTodos
import community.flock.wirespec.generated.spi.kotlin.endpoint.PutTodoById
import org.springframework.stereotype.Component

interface TodoClient :
    GetTodos.Handler,
    PutTodoById.Handler

@Component
class LiveTodoClient(
    private val wirespecClient: CustomWirespecWebClient,
) : TodoClient {
    override suspend fun getTodos(request: GetTodos.Request): GetTodos.Response<*> = wirespecClient.send(request)

    override suspend fun putTodoById(request: PutTodoById.Request): PutTodoById.Response<*> = wirespecClient.send(request)
}
