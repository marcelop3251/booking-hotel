package com.tcc.bookinghotel

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.web.reactive.function.server.coRouter

fun main() = runBlocking {

    val job = async {
        println("Iniciando processamento job 1 async")
        delay(10)
        println("finalizando processamento job 1 async")
        return@async 1
    }

    val job2 = async {
        println("Iniciando processamento job 2 async")
        delay(2)
        println("finalizando processamento job 2 async")
        return@async 2
    }
    println("Thread normal")
    teste(job.await(), job2.await())
    println("finalizando processamento")
}

suspend fun teste(value: Int, value2: Int) {
    println("value $value e value2 $value2")
}
