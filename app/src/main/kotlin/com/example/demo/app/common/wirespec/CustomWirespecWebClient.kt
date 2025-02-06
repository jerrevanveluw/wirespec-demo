package com.example.demo.app.common.wirespec

import community.flock.wirespec.integration.spring.shared.filterNotEmpty
import community.flock.wirespec.kotlin.Wirespec
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.util.CollectionUtils.toMultiValueMap
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.reflect.full.companionObjectInstance

@Configuration
class AppConfig {
    @Bean
    fun webClient(builder: WebClient.Builder) =
        builder
            .baseUrl("http://localhost:8080")
            .build()
}

@Component
class CustomWirespecWebClient(
    private val client: WebClient,
) {
    private val serialization = CustomWirespecSerialization

    @Suppress("UNCHECKED_CAST")
    suspend fun <Req : Wirespec.Request<*>, Res : Wirespec.Response<*>> send(request: Req): Res {
        val declaringClass = request::class.java.declaringClass
        val handler =
            declaringClass.declaredClasses
                .toList()
                .find { it.simpleName == "Handler" }
                ?: error("Handler not found")
        val instance = handler.kotlin.companionObjectInstance as Wirespec.Client<Req, Res>
        return with(instance.client(serialization)) { executeRequest(to(request), client).let(::from) }
    }

    private suspend fun executeRequest(
        request: Wirespec.RawRequest,
        client: WebClient,
    ): Wirespec.RawResponse =
        client
            .method(HttpMethod.valueOf(request.method))
            .uri { uriBuilder ->
                uriBuilder
                    .path(request.path.joinToString("/"))
                    .apply { request.queries.filterNotEmpty().forEach { (key, value) -> queryParam(key, value) } }
                    .build()
            }.headers { headers ->
                request.headers.filterNotEmpty().forEach { (key, value) -> headers.addAll(key, value) }
            }.apply {
                request.body?.let {
                    contentType(MediaType.APPLICATION_JSON)
                    bodyValue(it)
                }
            }.exchangeToMono { response ->
                response
                    .bodyToMono(ByteArray::class.java)
                    .map { body ->
                        Wirespec.RawResponse(
                            statusCode = response.statusCode().value(),
                            headers = toMultiValueMap(response.headers().asHttpHeaders()),
                            body = body,
                        )
                    }.switchIfEmpty(
                        Mono.just(
                            Wirespec.RawResponse(
                                statusCode = response.statusCode().value(),
                                headers = toMultiValueMap(response.headers().asHttpHeaders()),
                                body = null,
                            ),
                        ),
                    )
            }.onErrorResume { throwable ->
                when (throwable) {
                    is WebClientResponseException ->
                        Wirespec
                            .RawResponse(
                                statusCode = throwable.statusCode.value(),
                                headers = toMultiValueMap(throwable.headers),
                                body = throwable.responseBodyAsByteArray,
                            ).let { Mono.just(it) }

                    else -> Mono.error(throwable)
                }
            }.awaitSingle()
}
