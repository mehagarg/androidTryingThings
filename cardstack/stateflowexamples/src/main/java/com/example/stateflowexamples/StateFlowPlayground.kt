package com.example.stateflowexamples

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
fun main() = runBlocking{
    val stateFlow = MutableStateFlow<Int>(0)

    // Observe values
    val job = launch {
        stateFlow.collect {
            print(it)
        }
    }

    // Change values
    (1..5).forEach{
        delay(500)
        stateFlow.value = it
    }

    job.cancel()
    job.join()
}