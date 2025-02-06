package com.example.demo.app.common

import com.example.demo.app.common.UUIDTransformer.consume
import com.example.demo.app.common.UUIDTransformer.produce
import java.util.UUID

object UUIDTransformer : SymmetricTransformer<String, UUID> {
    override fun String.consume(): UUID = UUID.fromString(this)

    override fun UUID.produce(): String = toString()
}

object UUIDConverter : SymmetricConverter<String, UUID> {
    override fun String.internalize(): UUID = consume()

    override fun UUID.externalize(): String = produce()
}
