package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

fun main(){
    foo("Some Value Outer Fun")
    // I dont have access to inner `bar` function.

    println(
        accumulate(
            32
        )
    )
}

/*
 Local Function is a concept of function within a function.
 Advnatages:
 Where we want to do some code reuse.
 Grouping that into a smaller fun with is good enough.
*/

fun foo(fooParam: String) {

    val outerFunction = "Value in Outer fun"
    fun bar(barParam: String = "Inner Fun"){
        println(barParam)

       /* I also have access to outer function's param
        So inner functions have access to varibles of outer func including its own*/
        println(fooParam)
        println(outerFunction)
    }
    bar()
}


fun accumulate(number: Int) : Int {
    var givenNumber = number
    fun add() { givenNumber++ }
    for(i in 1..10) {
        add()
    }
    return givenNumber
}