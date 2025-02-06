package com.example.demo.app.common.wirespec

import community.flock.wirespec.kotlin.Wirespec
import kotlin.reflect.KType

object CustomWirespecSerialization : Wirespec.Serialization {
    override fun <T> serializeBody(
        t: T,
        kType: KType,
    ): ByteArray {
        TODO("Not yet implemented")
    }

    override fun <T> serializePath(
        t: T,
        kType: KType,
    ): String {
        TODO("Not yet implemented")
    }

    override fun <T> serializeParam(
        value: T,
        kType: KType,
    ): List<String> {
        TODO("Not yet implemented")
    }

    override fun <T> deserializeBody(
        raw: ByteArray,
        kType: KType,
    ): T {
        TODO("Not yet implemented")
    }

    override fun <T> deserializePath(
        raw: String,
        kType: KType,
    ): T {
        TODO("Not yet implemented")
    }

    override fun <T> deserializeParam(
        values: List<String>,
        kType: KType,
    ): T {
        TODO("Not yet implemented")
    }
}
