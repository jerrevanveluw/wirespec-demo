package com.example.demo.app.common

interface SymmetricTransformer<EXTERNAL : Any, DOMAIN : Any> : Transformer<EXTERNAL, DOMAIN, EXTERNAL>

interface Transformer<POTENTIAL : Any, DOMAIN : Any, PRODUCED : Any> :
    Consumer<POTENTIAL, DOMAIN>,
    Producer<DOMAIN, PRODUCED>

interface Consumer<POTENTIAL : Any, DOMAIN : Any> {
    fun POTENTIAL.consume(): DOMAIN
}

interface Producer<DOMAIN : Any, PRODUCED : Any> {
    fun DOMAIN.produce(): PRODUCED
}
