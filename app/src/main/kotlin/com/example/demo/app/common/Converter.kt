package com.example.demo.app.common

interface SymmetricConverter<EXTERNAL : Any, DOMAIN : Any> : Converter<EXTERNAL, DOMAIN, EXTERNAL>

interface Converter<EXTERNAL : Any, DOMAIN : Any, EXTERNALIZED : Any> :
    Internalizer<EXTERNAL, DOMAIN>,
    Externalizer<DOMAIN, EXTERNALIZED>

interface Internalizer<EXTERNAL : Any, DOMAIN : Any> {
    fun EXTERNAL.internalize(): DOMAIN
}

interface Externalizer<DOMAIN : Any, EXTERNALIZED : Any> {
    fun DOMAIN.externalize(): EXTERNALIZED
}
