package com.example.demo.app.common

fun <POTENTIAL : Any, DOMAIN : Any, PRODUCED : Any> handle(
    transformer: Transformer<POTENTIAL, DOMAIN, PRODUCED>,
    potentialInput: POTENTIAL,
    block: (DOMAIN) -> DOMAIN,
): PRODUCED =
    with(transformer) {
        potentialInput
            .consume()
            .let(block)
            .produce()
    }
