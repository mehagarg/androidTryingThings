package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

fun String.shouldBeEqualTo(value: String) = this == value
infix fun String.shouldBeEqualToInfix(value: String) = this == value

fun main(){

    val output = "hello"
    // This is an extenstion fun or member fun that have single parameter
    // In kotlin we can call it infix notations
    println(output.shouldBeEqualTo("hello"))

    println(output shouldBeEqualToInfix "hello")
}