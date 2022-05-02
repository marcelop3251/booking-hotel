package com.tcc.bookinghotel

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

//fun main() = runBlocking {
//
//    val job = async {
//        println("Iniciando processamento job 1 async")
//        delay(10)
//        println("finalizando processamento job 1 async")
//        return@async 1
//    }
//
//    val job2 = async {
//        println("Iniciando processamento job 2 async")
//        delay(2)
//        println("finalizando processamento job 2 async")
//        return@async 2
//    }
//    println("Thread normal")
//    teste(job.await(), job2.await())
//    println("finalizando processamento")
//}
//
//suspend fun teste(value: Int, value2: Int) {
//    println("value $value e value2 $value2")
//}



fun simple(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(2000) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    // Launch a concurrent coroutine to check if the main thread is blocked
    // Collect the flow
    simple().map { value ->
        println(value)
        value
    }.collect()
}
