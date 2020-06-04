package com.example.advancedkotlinhadihaririplayground.deepdivefunctions

/***
 * Anonymous fun we talked about local returns
 * return to closest fun
 *
 */

fun <T> Iterable<T>.myForEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

// Behavior is also different with anonymous fun
fun anonymousFunEg() {
    val numbers = 1..100
    numbers.forEach(fun(element) {
        if (element % 5 == 0) {
            return
        }
    })
    println("Hello  anonymousFunEg")
}

fun containingFunctionMyForEach() {
    val numbers = 1..100
    numbers.myForEach myLabel@{
        if (it % 5 == 0) {
            return@myLabel
        }
    }
    println("Hello  containingFunctionMyForEach")
}

// Lets say we dont do return like below
fun containingFunctionMyForEachNoLocalRetun() {
    val numbers = 1..100
    numbers.myForEach {
        if (it % 5 == 0) {
//            return
            /** return is not allowed here because myForEach is not an inline fun
            // if you add inline modifier if will behave like `containingFunction` and return
            // without printing hello.*/
        }
    }
    println("Hello  containingFunctionMyForEachNoLocalRetun")
}

fun containingFunction(){
    val numbers = 1..100

    /**
     * for each has relation with inline fun
     */
    numbers.forEach {
        if(it%5==0){
            return
        }
    }
    println("Hello  containingFunction")
}

fun containingFunctionLocalReturn(){
    val numbers = 1..100
    numbers.forEach myLabel@{
        if(it%5==0){
//            return@forEach
            return@myLabel
        }
    }
    println("Hello  containingFunctionLocalReturn")
}

fun main(){
    /**
     * if you run the func, it finishes but it will not print `Hello`. The reason
     * is we are returning from the lambda fun `forEach` and it retursn from
     * containing fun. It will never execute print line hello.
     * What if we want if to print.
     * With Kotlin we can do with - local return
     */
    containingFunction()

    /**
     * This is going to do a local return. We are saying is return to the forEach, instead
     * of returning from the fun.
     */
    containingFunctionLocalReturn()

    containingFunctionMyForEach()

    anonymousFunEg()
}